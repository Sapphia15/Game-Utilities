package graphics.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.javase.api.awt.AWTFrameGrab;
import org.jcodec.javase.scale.AWTUtil;

import gameutil.text.Console;

public class VideoScreen extends Screen{
	
	private int frameNumber=0;
	AWTFrameGrab fg=null;
	CopyOnWriteArrayList<BufferedImage> images=new CopyOnWriteArrayList<>();
	BufferedImage image=null;
	SPanel observer;
	int draws=0;
	boolean complete=false;
	
	public VideoScreen(String file,SPanel observer) {
		this.observer=observer;
		try {
			fg=AWTFrameGrab.createAWTFrameGrab(NIOUtils.readableChannel(new File(file)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		observer.setBackgroundColor(Color.black);

	}
	
	@Override
	public void paint(Graphics g) {
		//if (image!=null) {
			
			int index=draws;
			if (index<images.size()) {
				image=images.get(index);
				if (image!=null) {
					int observerWidth=observer.getWidth();
					int height=observerWidth*image.getHeight()/image.getWidth();
					g.drawImage(image,0,(observer.getHeight()-height)/2,observerWidth,height,observer);
					draws++;
				}
			}
			
			
		//}
	}

	@Override
	public void update() {
		if (!complete) {
			/*Console.s.clr();
			Console.s.println(getPercent()*100+"%");
			Console.s.println(Math.floor(getCurrentTimestamp()*100)/100+"s / "+Math.floor(getDuration()*100)/100+"s");*/
			// TODO Auto-generated method stub
			try {
				
				//BufferedImage bufferedImage = fg.getFrame();
				for (int i=0;i<draws;i++) {
					try {
						images.remove(0);
						draws--;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				BufferedImage nextFrame=fg.getFrame();
				if (nextFrame==null) {
					complete=true;
					//Console.s.println("Video Complete!");
				} else {
					images.add(nextFrame);
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void playFromStart() {
		complete=false;
		try {
			fg.seekToFramePrecise(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JCodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public double getPercent() {
		return (double)(fg.getVideoTrack().getCurFrame())/fg.getVideoTrack().getMeta().getTotalFrames();
	}
	
	public double getDuration() {
		return fg.getVideoTrack().getMeta().getTotalDuration();
	}
	
	public double getCurrentTimestamp() {
		return getPercent()*getDuration();
	}
	
	public boolean videoComplete() {
		return complete;
	}

	@Override
	protected void keyDown(int key) {
		
	}
	
}
