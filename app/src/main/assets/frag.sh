precision mediump float;
//varying  vec4 vColor; //接收从顶点着色器过来的参数
varying vec2 vTextureCoord;//接收从顶点着色器过来的参数
uniform sampler2D sTexture;//纹理内容数据
void main()                         
{          
	//vec4 finalColor = vColor;      
	vec4 finalColor =  texture2D(sTexture, vTextureCoord);    
	gl_FragColor = finalColor;
}