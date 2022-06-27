package graphics.gl;

import org.joml.*;
import org.lwjgl.BufferUtils;

import javax.print.DocFlavor;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.lwjgl.opengl.ARBVertexArrayObject;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;


public class Shader {

    private int shaderProgramID;
    private boolean beingUsed = false;

    private String vertexSource;
    private String fragmentSource;
    private String filepath;
    
    private int vaoID, vboID, eboID;
    private int[] edgeArray;
    private float[] vertexArray;
    
    public Shader(String filepath) {
        this.filepath = filepath;
        try {
            String source = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

            // Find the first pattern after #type 'pattern'
            int index = source.indexOf("#type") + 6;
            int eol = source.indexOf("\r\n", index);
            String firstPattern = source.substring(index, eol).trim();

            // Find the second pattern after #type 'pattern'
            index = source.indexOf("#type", eol) + 6;
            eol = source.indexOf("\r\n", index);
            String secondPattern = source.substring(index, eol).trim();

            if (firstPattern.equals("vertex")) {
                vertexSource = splitString[1];
            } else if (firstPattern.equals("fragment")) {
                fragmentSource = splitString[1];
            } else {
                throw new IOException("Unexpected token '" + firstPattern + "'");
            }

            if (secondPattern.equals("vertex")) {
                vertexSource = splitString[2];
            } else if (secondPattern.equals("fragment")) {
                fragmentSource = splitString[2];
            } else {
                throw new IOException("Unexpected token '" + secondPattern + "'");
            }
        } catch(IOException e) {
            e.printStackTrace();
            assert false : "Error: Could not open file for shader: '" + filepath + "'";
        }
    }

    public void compile() {
        // ============================================================
        // Compile and link shaders
        // ============================================================
        int vertexID, fragmentID;

        // First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + filepath + "'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        // First load and compile the vertex shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        // Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + filepath + "'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        // Link shaders and check for errors
        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);

        // Check for linking errors
        success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '" + filepath + "'\n\tLinking of shaders failed.");
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            assert false : "";
        }
    }

    public void use() {
        if (!beingUsed) {
            // Bind shader program
            glUseProgram(shaderProgramID);
            beingUsed = true;
        }
    }

    public void detach() {
        glUseProgram(0);
        beingUsed = false;
    }

    public void uploadMat4f(String varName, Matrix4f mat4) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }

    public void uploadMat3f(String varName, Matrix3f mat3) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        mat3.get(matBuffer);
        glUniformMatrix3fv(varLocation, false, matBuffer);
    }

    public void uploadVec4f(String varName, Vector4f vec) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform4f(varLocation, vec.x, vec.y, vec.z, vec.w);
    }

    public void uploadVec3f(String varName, Vector3f vec) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform3f(varLocation, vec.x, vec.y, vec.z);
    }

    public void uploadVec2f(String varName, Vector2f vec) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform2f(varLocation, vec.x, vec.y);
    }

    public void uploadFloat(String varName, float val) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1f(varLocation, val);
    }

    public void uploadInt(String varName, int val) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1i(varLocation, val);
    }

    public void uploadTexture(String varName, int slot) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1i(varLocation, slot);
    }

    public void uploadIntArray(String varName, int[] array) {
        int varLocation = glGetUniformLocation(shaderProgramID, varName);
        use();
        glUniform1iv(varLocation, array);
    }
    
    public void uploadFlags(float[] vertexArray,int[] edgeArray) {
    	this.vertexArray=vertexArray;
    	this.edgeArray=edgeArray;
    	//vertex flags
    	
    	//make vertex buffer
    	vaoID=ARBVertexArrayObject.glGenVertexArrays();
    	GL30.glBindVertexArray(vaoID);
    	FloatBuffer vertexBuffer=BufferUtils.createFloatBuffer(vertexArray.length);
    	vertexBuffer.put(vertexArray).flip();//why does it flip them?
    	
    	vboID=GL30.glGenBuffers();
    	GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboID);
    	GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexBuffer, GL30.GL_STATIC_DRAW);
    	
    	//edge flags
    	
    	//make edge buffer
    	IntBuffer edgeBuffer=BufferUtils.createIntBuffer(edgeArray.length);
    	edgeBuffer.put(edgeArray).flip();
    	
    	eboID=GL30.glGenBuffers();
    	GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, eboID);
    	GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, edgeBuffer, GL30.GL_STATIC_DRAW);
    	
    	int posSize=3;
    	int colorSize=4;
    	int floatSizeBytes=4;
    	int vertexSizeBytes=(posSize+colorSize)*floatSizeBytes;
    	GL30.glVertexAttribPointer(0, posSize, GL30.GL_FLOAT, false, vertexSizeBytes,0);
    	GL30.glEnableVertexAttribArray(0);
    	GL30.glVertexAttribPointer(1, colorSize, GL30.GL_FLOAT, false, vertexSizeBytes,posSize*floatSizeBytes);
    	GL30.glEnableVertexAttribArray(1);
    }
    
    public void uploadFlags4D(float[] vertexArray,int[] edgeArray) {
    	this.vertexArray=vertexArray;
    	this.edgeArray=edgeArray;
    	//vertex flags
    	
    	//make vertex buffer
    	vaoID=ARBVertexArrayObject.glGenVertexArrays();
    	GL30.glBindVertexArray(vaoID);
    	FloatBuffer vertexBuffer=BufferUtils.createFloatBuffer(vertexArray.length);
    	vertexBuffer.put(vertexArray).flip();//why does it flip them?
    	
    	vboID=GL30.glGenBuffers();
    	GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboID);
    	GL30.glBufferData(GL30.GL_ARRAY_BUFFER, vertexBuffer, GL30.GL_STATIC_DRAW);
    	
    	//edge flags
    	
    	//make edge buffer
    	IntBuffer edgeBuffer=BufferUtils.createIntBuffer(edgeArray.length);
    	edgeBuffer.put(edgeArray).flip();
    	
    	eboID=GL30.glGenBuffers();
    	GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, eboID);
    	GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, edgeBuffer, GL30.GL_STATIC_DRAW);
    	
    	int posSize=4;
    	int colorSize=4;
    	int floatSizeBytes=4;
    	int vertexSizeBytes=(posSize+colorSize)*floatSizeBytes;
    	GL30.glVertexAttribPointer(0, posSize, GL30.GL_FLOAT, false, vertexSizeBytes,0);
    	GL30.glEnableVertexAttribArray(0);
    	GL30.glVertexAttribPointer(1, colorSize, GL30.GL_FLOAT, false, vertexSizeBytes,posSize*floatSizeBytes);
    	GL30.glEnableVertexAttribArray(1);
    }
    
    public void updatePolygon() {
    	use();
    	GL30.glBindVertexArray(vaoID);
    	GL30.glEnableVertexAttribArray(0);
    	GL30.glEnableVertexAttribArray(1);
    	
    	GL20.glDrawElements(GL30.GL_TRIANGLES, edgeArray.length,GL30.GL_UNSIGNED_INT,0);
    	
    	//GL20.glDrawElements(GL30.GL_LINES, edgeArray.length,GL30.GL_UNSIGNED_INT,0);
    	
    	
    	GL20.glDisableVertexAttribArray(0);
    	GL20.glDisableVertexAttribArray(1);
    	GL30.glBindVertexArray(0);
    	detach();
    }
}
