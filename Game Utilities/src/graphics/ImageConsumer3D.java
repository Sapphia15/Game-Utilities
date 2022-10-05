package graphics;

import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;

public interface ImageConsumer3D extends ImageConsumer{
	
	/**
     * The dimensions of the source image are reported using the
     * setDimensions method call.
     * @param width the width of the source image
     * @param length the length of the source image
     * @param height the height of the source image
     */
    void setDimensions(int width, int length, int height);
	
	/**
     * Delivers the pixels of the image with one or more calls
     * to this method.  Each call specifies the location and
     * size of the rectangle of source pixels that are contained in
     * the array of pixels.  The specified ColorModel object should
     * be used to convert the pixels into their corresponding color
     * and alpha components.  Pixel (m,n) is stored in the pixels array
     * at index (n * scansize + m + off).  The pixels delivered using
     * this method are all stored as bytes.
     * @param x the X coordinate of the upper-left-inner corner of the
     *        volume of pixels to be set
     * @param y the Y coordinate of the upper-left-inner corner of the
     *        volume of pixels to be set
     * @param z the Z coordinate of the upper-left-inner corner of the
     *        volume of pixels to be set
     * @param w the width of the volume of pixels
     * @param l the length of the volume of pixels
     * @param h the height of the volume of pixels
     * @param model the specified {@code ColorModel}
     * @param pixels the array of pixels
     * @param off the offset into the {@code pixels} array
     * @param scansize the distance from one row of pixels to the next in
     * the {@code pixels} array
     * @see ColorModel
     */
    void setVoxels(int x, int y, int z, int w, int l, int h,
                   ColorModel model, byte[] pixels, int off, int scansize);

    /**
     * The pixels of the image are delivered using one or more calls
     * to the setPixels method.  Each call specifies the location and
     * size of the rectangle of source pixels that are contained in
     * the array of pixels.  The specified ColorModel object should
     * be used to convert the pixels into their corresponding color
     * and alpha components.  Pixel (m,n) is stored in the pixels array
     * at index (n * scansize + m + off).  The pixels delivered using
     * this method are all stored as ints.
     * this method are all stored as ints.
     * @param x the X coordinate of the upper-left-inner corner of the
     *        volume of pixels to be set
     * @param y the Y coordinate of the upper-left-inner corner of the
     *        volume of pixels to be set
     * @param z the Z coordinate of the upper-left-inner corner of the
     *        volume of pixels to be set
     * @param w the width of the volume of pixels
     * @param l the length of the volume of pixels
     * @param h the height of the volume of pixels
     * @param model the specified {@code ColorModel}
     * @param pixels the array of pixels
     * @param off the offset into the {@code pixels} array
     * @param scansize the distance from one row of pixels to the next in
     * the {@code pixels} array
     * @see ColorModel
     */
    void setVoxels(int x, int y, int z, int w,int l, int h,
                   ColorModel model, int[] pixels, int off, int scansize);
	
    int flatten(int x, int y, int z);
    
    int flatten(int[] p);
    
    int[] volumize(int n);
    
    void setVoxels(int x, int y, int z, int w, int l, int h, byte[] voxels);
    
    void setVoxels(int x, int y, int z, int w, int l, int h, int[] voxels);
}
