#type vertex
#version 330 core
layout (location=0) in vec4 apos;
layout (location=1) in vec4 aColor;

uniform mat4 uView;
uniform mat4 uProj;

out vec4 fColor;



void main()
{
    fColor = aColor;

    gl_Position = uView * aPos;
   
}

#type fragment
#version 330 core

in vec4 fColor;

out vec4 color;

void main()
{
    color = fColor;
}