package com.opengl.zouyu;

import static com.opengl.zouyu.Constant.*;

import com.opengl.util.Vector3f;


public class ParticleControl {
	Particle particles[][] = new Particle[NUMROWS+1][NUMCOLS+1];//粒子数组
	Spring springs[] = new Spring[NUMSPTINGS];//弹簧数组
    float vertices[]=new float[NUMCOLS*NUMROWS*2*3*3];//每个顶点xyz三个坐标
    Collision collisions[] = new Collision[NUMVERTICES*2];//碰撞体数组
    Vector3f temp = new Vector3f(0, 0, 0);//临时向量对象1
    Vector3f temp2 = new Vector3f(0, 0, 0);//临时向量对象2
	public ParticleControl()
	{
		initalize();
	}
	public float[] getVerties()//获取一帧数据
	{
	    int count=0;//顶点计数器
        for(int r=0;r<NUMROWS;r++)
        {
        	for(int c=0;c<NUMCOLS;c++)
        	{        		
        		vertices[count++]=particles[r][c].pvPosition.x;
        		vertices[count++]=particles[r][c].pvPosition.y;
        		vertices[count++]=particles[r][c].pvPosition.z;
        		
        		vertices[count++]=particles[r+1][c].pvPosition.x;
        		vertices[count++]=particles[r+1][c].pvPosition.y;
        		vertices[count++]=particles[r+1][c].pvPosition.z;
        		
        		vertices[count++]=particles[r][c+1].pvPosition.x;
        		vertices[count++]=particles[r][c+1].pvPosition.y;
        		vertices[count++]=particles[r][c+1].pvPosition.z;
        		
        		vertices[count++]=particles[r][c+1].pvPosition.x;
        		vertices[count++]=particles[r][c+1].pvPosition.y;
        		vertices[count++]=particles[r][c+1].pvPosition.z;
        		
        		vertices[count++]=particles[r+1][c].pvPosition.x;
        		vertices[count++]=particles[r+1][c].pvPosition.y;
        		vertices[count++]=particles[r+1][c].pvPosition.z;
        		
        		vertices[count++]=particles[r+1][c+1].pvPosition.x;
        		vertices[count++]=particles[r+1][c+1].pvPosition.y;
        		vertices[count++]=particles[r+1][c+1].pvPosition.z;
        	}
        }
		return vertices;
	}
	public void initalize()//初始化粒子系统数据
	{
		for(int r=0;r<=NUMROWS;r++)
		{
			for(int c=0;c<=NUMCOLS;c++)
			{//为不同区域的粒子设置不同的重量
				particles[r][c] = new Particle();
				float f;
				if(((r==0)&&(c==0))||((r==NUMROWS)&&(c==NUMCOLS))){
					f = 1;
				}
				else if(((r==NUMROWS)&&(c==0))||((r==0)&&(c==NUMCOLS))){
					f=2;
				}
				else if(((r==0)&&((c!=0)&&(c!=NUMCOLS)))||((r==NUMROWS)&&((c!=0)&&(c!=NUMCOLS)))){
					f=3;
				}
				else{
					f=6;
				}
				f = f/8;
				//设置质量,计算质量倒数
				particles[r][c].pfMass = f;
				particles[r][c].pfInvMass = 1/particles[r][c].pfMass;
				//计算初始化位置
				particles[r][c].pvPosition.x = FLAGPOLERADIUS+c * CSTER;
				particles[r][c].pvPosition.y = RSTER*NUMROWS/2-r * RSTER;
				particles[r][c].pvPosition.z = 0;
				//设置不动粒子
				if((r==0&&c==0)||(r==NUMROWS&&c==0))
				{
					particles[r][c].bLocked = true;
				}
				else
				{
					particles[r][c].bLocked = false;
				}
			}
		}
		//初始化弹簧
		int count = 0;//计数器
		for(int r=0;r<=NUMROWS;r++)
		{
			for(int c=0;c<=NUMCOLS;c++)
			{
				
				if(c<NUMCOLS)//初始化竖弹簧
				{
					springs[count] = new Spring();
					//第一个连接点
					springs[count].p1.r = r;
					springs[count].p1.c = c;
					//第二个连接点
					springs[count].p2.r = r;
					springs[count].p2.c = c+1;
					//计算长度
					temp.voluation(particles[r][c].pvPosition);
					temp.sub(particles[r][c+1].pvPosition);
					springs[count].L = temp.module();
					count++;
				}
				if(r<NUMROWS)//初始化横弹簧
				{
					springs[count] = new Spring();
					//第一个连接点
					springs[count].p1.r = r;
					springs[count].p1.c = c;
					//第二个连接点
					springs[count].p2.r = r+1;
					springs[count].p2.c = c;
					//计算长度
					temp.voluation(particles[r][c].pvPosition);
					temp.sub(particles[r+1][c].pvPosition);
					springs[count].L = temp.module();
					count++;
				}
				if(r<NUMROWS&&c<NUMCOLS)//初始化左上右下弹簧
				{
					springs[count] = new Spring();
					springs[count].k = SPRING_SHEAR_CONSTANT;
					//第一个连接点
					springs[count].p1.r = r;
					springs[count].p1.c = c;
					//第二个连接点
					springs[count].p2.r = r+1;
					springs[count].p2.c = c+1;
					//计算长度
					temp.voluation(particles[r][c].pvPosition);
					temp.sub(particles[r+1][c+1].pvPosition);
					springs[count].L = temp.module();
					count++;
				}
				if(r<NUMROWS&&c>0)//初始化右上左下弹簧
				{
					springs[count] = new Spring();
					springs[count].k = SPRING_SHEAR_CONSTANT;
					//第一个连接点
					springs[count].p1.r = r;
					springs[count].p1.c = c;
					//第二个连接点
					springs[count].p2.r = r+1;
					springs[count].p2.c = c-1;
					//计算长度
					temp.voluation(particles[r][c].pvPosition);
					temp.sub(particles[r+1][c-1].pvPosition);
					springs[count].L = temp.module();
					count++;
				}
			}
		}
		//初始化碰撞数组
		for(int i=0; i<NUMVERTICES*2; i++)
		{
			collisions[i] = new Collision();
		}
	}
	public void calcForces()//计算力的方法
	{
		//将所有粒子受力至0
		for(int r=0;r<=NUMROWS;r++)
		{
			for(int c=0;c<=NUMCOLS;c++)
			{
				particles[r][c].pvForces.x = 0;
				particles[r][c].pvForces.y = 0;
				particles[r][c].pvForces.z = 0;
			}
		}
		//计算重力阻力与风力
		for(int r=0;r<=NUMROWS;r++)
		{
			for(int c=0;c<=NUMCOLS;c++)
			{
				if(!particles[r][c].bLocked)
				{
					//重力
					particles[r][c].pvForces.y += GRAVITY*particles[r][c].pfMass;
								
					//粘滞阻力=当前粒子速度反方向单位向量*速度大小平方*风阻参数
					temp.voluation(particles[r][c].pvVelocity);
					temp.normalize();
					temp.scale(-particles[r][c].pvVelocity.moduleSq()*DRAGCOEFFICIENT);
					particles[r][c].pvForces.add(temp);
					
					//风力=随机风向*随机风力
					temp.voluation((float)(Math.random()*1), 0, (float)(Math.random()*0.3f));
					temp.scale((float)(Math.random()*WindForce));
					particles[r][c].pvForces.add(temp);
				}
			}
		}
		
		for(int i=0;i<NUMSPTINGS;i++)
		{
			int r1 = (int) springs[i].p1.r;
			int c1 = (int) springs[i].p1.c;
			int r2 = (int) springs[i].p2.r;
			int c2 = (int) springs[i].p2.c;

			temp.voluation(particles[r1][c1].pvPosition);
			temp.sub(particles[r2][c2].pvPosition);//计算粒子间距离
			float pd = temp.module();

			temp2.voluation(particles[r1][c1].pvVelocity);
			temp2.sub(particles[r2][c2].pvVelocity);//计算速度差
			
			float L = springs[i].L;
			//根据弹簧公式计算弹力
			float t = -(springs[i].k*(pd-L)+springs[i].d*(temp.dotProduct(temp2)/pd))/pd;
			temp.scale(t);
			
			if(!particles[r1][c1].bLocked)
			{
				particles[r1][c1].pvForces.add(temp);
			}
			if(!particles[r2][c2].bLocked)
			{
				temp.scale(-1);
				particles[r2][c2].pvForces.add(temp);
			}
		}
	}
	public boolean checkForCollisions()
	{
		int	count = 0;
		boolean	state = false;
		//清除上一轮碰撞信息
		for(int i=0; i<collisions.length; i++){
			collisions[i].r = -1;
		}
		//检测粒子与地面的碰撞
		for(int r=0; r<=NUMROWS; r++)
		{
			for(int c=0; c<=NUMCOLS; c++)
			{
				if(!particles[r][c].bLocked)
				{
					if((particles[r][c].pvPosition.y <= COLLISIONTOLERANCE) && (particles[r][c].pvVelocity.y < 0f))
					{
						state = true;
						collisions[count].r = r;
						collisions[count].c = c;
						collisions[count].n.x = 0.0f;
						collisions[count].n.y = 1.0f;
						collisions[count].n.z = 0.0f;
						count++;
					}
				}
			}
		}		
		//检测粒子与旗杆的碰撞
		for(int r=0; r<=NUMROWS; r++)
		{
			for(int c=0; c<=NUMCOLS; c++)
			{			
				if(!particles[r][c].bLocked)
				{
					//获得此粒子位置距旗杆中线的投影点距离
					float fd = (particles[r][c].pvPosition.x)*(particles[r][c].pvPosition.x)+
								(particles[r][c].pvPosition.z)*(particles[r][c].pvPosition.z);
					temp.voluation(particles[r][c].pvPosition.x,0,particles[r][c].pvPosition.z);
					if((fd <= FLAGPOLERADIUS) && (temp.dotProduct(particles[r][c].pvVelocity)>0f))
					{
						state = true;
						collisions[count].r = r;
						collisions[count].c = c;
						collisions[count].n.voluation(temp);
						collisions[count].n.normalize();
						count++;
					} 
				}
			}
		}
		return state;
	}
	public void	resolveCollisions()
	{	
		for(int i=0; i<collisions.length; i++)
		{
			if(collisions[i].r != -1)
			{
				int r = collisions[i].r;//获取行列
				int c = collisions[i].c;
				temp.voluation(collisions[i].n);//获取碰撞法向量
				temp.scale(temp.dotProduct(particles[r][c].pvVelocity));//求得法向量方向的速度分量
				temp2.voluation(particles[r][c].pvVelocity);//获取速度
				temp2.sub(temp);//减去法向量方向分量得切向分量
				temp.scale(-KRESTITUTION);//法向量方向速度乘以反弹系数
				temp2.scale(FRICTIONFACTOR);//切向方向速度乘以动摩擦系数
				temp.add(temp2);//计算出新的速度
				particles[r][c].pvVelocity.voluation(temp);
			}
		}
	}
	public void stepSimulation(float dt)
	{
		calcForces();//物理计算
		for(int r=0;r<=NUMROWS;r++)
		{
			for(int c=0;c<=NUMCOLS;c++)
			{
				temp.voluation(particles[r][c].pvForces);
				temp.scale(particles[r][c].pfInvMass);//计算加速度
				particles[r][c].pvAcceleration.voluation(temp);
				temp.scale(dt);//加速度乘进步时间
				particles[r][c].pvVelocity.add(temp);//计算新的速度
				temp.voluation(particles[r][c].pvVelocity);
				temp.scale(dt);//加速度乘进步时间
				particles[r][c].pvPosition.add(temp);//计算新的位置
				if(particles[r][c].pvPosition.y<COLLISIONTOLERANCE)
				{
					particles[r][c].pvPosition.y=COLLISIONTOLERANCE;
				}
			}
		}
		if(isC)
		{
			if(checkForCollisions())
			{
				resolveCollisions();
			}
		}
	}
}
