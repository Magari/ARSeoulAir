package com.ARSeoulAir;

/*******************************************************************************
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

/**
 * Encapsulates a 2D vector. Allows chaining methods by returning a 
 * reference to itself
 * @author badlogicgames@gmail.com
 *
 */
public final class Vector2 
{
	 public float []vec = new float[2]; 

	    public static final Vector2 ZERO = new Vector2(0, 0);
	    
	    
	    public Vector2(float[] v) {
	    	set(v[0], v[1]);
	    }
	    
	    public Vector2(float xValue, float yValue) {
	        set(xValue, yValue);
	    }

	    public Vector2(Vector2 other) {
	        set(other);
	    }

	    public final void add(Vector2 other) {
	        vec[0] += other.vec[0];
	        vec[1] += other.vec[1];
	  
	    }
	    
	    public final void add(float otherX, float otherY, float otherZ) {
	    	vec[0] += otherX;
	    	vec[1] += otherY;
	   
	    }

	    public final void subtract(Vector2 other) {
	    	vec[0] -= other.vec[0];
	    	vec[1] -= other.vec[1];
	  
	    }

	    public final void multiply(float magnitude) {
	    	vec[0] *= magnitude;
	    	vec[1] *= magnitude;
	    	
	    }
	    
	    public final void multiply(Vector2 other) {
	    	vec[0] *= other.vec[0];
	    	vec[1] *= other.vec[1];
	    	
	    }

	    public final void divide(float magnitude) {
	        if (magnitude != 0.0f) {
	        	vec[0] /= magnitude;
	        	vec[1] /= magnitude;
	        	
	        }
	    }

	    public final void set(Vector2 other) {
	    	vec[0] = other.vec[0];
	        vec[1] = other.vec[1];
	    
	    }

	    public final void set(float xValue, float yValue) {
	    	vec[0] = xValue;
	    	vec[1] = yValue;
	    
	    }


	    /*public final Vector2 cross(Vector2 v)
	    {
	    	return new Vector2();
	    }
	   */ 
	    /*  private Vector2 Vector2(float x, float y, float z) {
			// TODO Auto-generated method stub
			return new Vector2(x, y, z);
		}
	   */
		public final float dot(Vector2 other) {
	        return (vec[0] * other.vec[0]) + (vec[1] * other.vec[1]);
	    }

	    public final float length() {
	        return (float) Math.sqrt(length2());
	    }

	    public final float length2() {
	        return (vec[0] * vec[0]) + (vec[1] * vec[1]);
	    }
	    
	    public final float distance2(Vector2 other) {
	        float dx = vec[0] - other.vec[0];
	        float dy = vec[1] - other.vec[1];
	  
	        return (dx * dx) + (dy * dy);
	    }

	    public final float normalize() {
	        final float magnitude = length();

	        // TODO: I'm choosing safety over speed here.
	        if (magnitude != 0.0f) {
	        	vec[0] /= magnitude;
	        	vec[1] /= magnitude;
	        
	        }

	        return magnitude;
	    }

	    public final void zero() {
	        set(0.0f, 0.0f);
	    }
}
