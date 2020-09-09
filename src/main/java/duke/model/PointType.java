package duke.model;

// the order is important here: if multiple events happen at the same point,
// this is the order in which you want to deal with them

/**
 * Point type class to represent 4 types of point.
 */
public enum PointType { End, GapEnd, GapStart, Start }
