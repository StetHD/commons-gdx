package com.gemserk.commons.gdx.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ImmediateModeRendererUtils {

	private static final ImmediateModeRenderer renderer = new ImmediateModeRenderer();

	private static final Vector2 tmp = new Vector2();

	private static final Vector2 angleTmp = new Vector2(1, 0);

	public static void drawSolidCircle(Circle circle, float angle, Color color) {
		drawSolidCircle(circle.x, circle.y, circle.radius, angle, color);
	}

	public static void drawSolidCircle(Vector2 center, float radius, float axisAngle, Color color) {
		drawSolidCircle(center.x, center.y, radius, axisAngle, color);
	}

	public static void drawSolidCircle(float x, float y, float radius, float axisAngle, Color color) {
		angleTmp.set(1, 0);
		angleTmp.rotate(axisAngle);
		drawSolidCircle(x, y, radius, angleTmp, color);
	}

	public static void drawSolidCircle(Vector2 center, float radius, Vector2 axis, Color color) {
		drawSolidCircle(center.x, center.y, radius, axis, color);
	}

	public static void drawSolidCircle(float x, float y, float radius, Vector2 axis, Color color) {
		drawSolidCircle(x, y, radius, color);
		drawLine(x, y, x + axis.x * radius, y + axis.y * radius, color);
	}

	public static void drawSolidCircle(Vector2 center, float radius, Color color) {
		drawSolidCircle(center.x, center.y, radius, color);
	}

	public static void drawSolidCircle(float x, float y, float radius, Color color) {
		renderer.begin(GL10.GL_LINE_LOOP);
		{
			float angle = 0;
			float angleInc = 2 * (float) Math.PI / 20;
			for (int i = 0; i < 20; i++, angle += angleInc) {
				tmp.set((float) Math.cos(angle) * radius + x, (float) Math.sin(angle) * radius + y);
				renderer.color(color.r, color.g, color.b, color.a);
				renderer.vertex(tmp.x, tmp.y, 0);
			}
		}
		renderer.end();
	}

	public static void drawLine(Vector2 p0, Vector2 p1, Color color) {
		drawLine(p0.x, p0.y, p1.x, p1.y, color);
	}

	public static void drawLine(float x0, float y0, float x1, float y1, Color color) {
		renderer.begin(GL10.GL_LINES);
		{
			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x0, y0, 0);
			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x1, y1, 0);
		}
		renderer.end();
	}

	public static void drawHorizontalAxis(float y, Color color) {
		drawHorizontalAxis(y, 10000, color);
	}

	public static void drawVerticalAxis(float x, Color color) {
		drawVerticalAxis(x, 10000, color);
	}

	public static void drawHorizontalAxis(float y, float length, Color color) {
		drawLine(-length, y, length, y, color);
	}

	public static void drawVerticalAxis(float x, float length, Color color) {
		drawLine(x, -length, x, length, color);
	}

	public static void drawRectangle(Rectangle r, Color color) {
		drawRectangle(r.x, r.y, r.x + r.getWidth(), r.y + r.getHeight(), color);
	}

	public static void drawRectangle(float x0, float y0, float x1, float y1, Color color) {
		renderer.begin(GL10.GL_LINE_LOOP);
		{
			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x0, y0, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x0, y1, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x1, y1, 0f);

			renderer.color(color.r, color.g, color.b, color.a);
			renderer.vertex(x1, y0, 0f);
		}
		renderer.end();
	}

}
