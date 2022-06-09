package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan Zilberstein
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;
	private int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		size = vertices.length;
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}


	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		//check if the ray intersection the plan
		List<GeoPoint> geoPointListFromPlane = plane.findGeoIntersectionsHelper(ray, maxDistance);

		if(geoPointListFromPlane == null) { return null; }

		//p= intersection point with the plan
		GeoPoint p = geoPointListFromPlane.get(0);

		//if the point on one of Vertex -> return null
		for (Point point:vertices) {
			if(p.point.equals(point))
				return null;
		}
		List<Vector> vectorToCheckDirection = new ArrayList<>();

		for (int i = 1; i < this.size; i++){
			Vector v1 = vertices.get(i).subtract(vertices.get(i-1)).normalize();
			Vector v2 = vertices.get(i-1).subtract(p.point).normalize();

			//if v1==v2-> the point on one of the edge or edge's continuation -> return null
			if(v1.equals(v2.scale(-1)) || v1.equals(v2))
				return null;
			vectorToCheckDirection.add(v1.crossProduct(v2));
		}
		Vector v1 = vertices.get(0).subtract(vertices.get(size-1)).normalize();
		Vector v2 = vertices.get(size-1).subtract(p.point).normalize();
		//if v1==v2-> the point on one of the edge or edge's continuation.
		if(v1.equals(v2.scale(-1)) || v1.equals(v2))
			return null;
		vectorToCheckDirection.add(v1.crossProduct(v2));

		if (checkTheVectorsDirection(vectorToCheckDirection)){
			List<GeoPoint> geoPointsPolygon = new ArrayList<>();
			for (GeoPoint geoPoint: geoPointListFromPlane)
			{
				geoPointsPolygon.add(new GeoPoint(this , geoPoint.point));
			}
			return geoPointsPolygon;
		}
		return null;
	}

	//region help method

	/**
	 * return if all the vectors in the same direction
	 * @param vectorToCheckDirection - list of vectors to check them direction.
	 * @return if all the vectors in the same direction.
	 */
	private boolean checkTheVectorsDirection(List<Vector> vectorToCheckDirection){
		for (Vector vector:vectorToCheckDirection) {
			if(vectorToCheckDirection.get(0).dotProduct(vector)<0)
				return false;
		}
		return true;
	}

	//endregion


}
