import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
//I`m using my own Line case, this way it will be a lot easier to implement the reflection, color and size of the line
public class Line {

	public ArrayList<Point> point;
	public Color color;
	public Integer size=5;
	public boolean ref;
	public Line(Color color, Integer size, boolean ref) {
		
		point = new ArrayList<Point>();
		this.color = color;
		this.size = size;
		this.ref = ref;
	}
	public void add(Point p){
		point.add(p);
	}

	public ArrayList<Point> returnPoints() {
		return point;
	}

	public Color returnColor() {
		return color;
	}

	public Integer returnSize() {
		return size;
	}

	public boolean Reflect() {
		return ref;
	}

	
}
