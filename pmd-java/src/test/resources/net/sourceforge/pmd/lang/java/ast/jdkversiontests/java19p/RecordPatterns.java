/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

/**
 * @see <a href="https://openjdk.org/jeps/405">JEP 405: Record Patterns (Preview)</a>
 */
public class RecordPatterns {

    record Point(int x, int y) {}
    enum Color { RED, GREEN, BLUE }
    record ColoredPoint(Point p, Color c) {}
    record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {}

    void printSum1(Object o) {
        if (o instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x+y);
        }
    }

    // record pattern
    void printSum2(Object o) {
        if (o instanceof Point(int x, int y)) {
            System.out.println(x+y);
        }
    }

    void printUpperLeftColoredPoint(Rectangle r) {
        if (r instanceof Rectangle(ColoredPoint ul, ColoredPoint lr)) {
            System.out.println(ul.c());
        }
    }

    // nested record pattern
    void printColorOfUpperLeftPoint(Rectangle r) {
        if (r instanceof Rectangle(ColoredPoint(Point p, Color c),
                ColoredPoint lr)) {
            System.out.println(c);
        }
    }

    // fully nested record pattern, also using "var"
    void printXCoordOfUpperLeftPointWithPatterns(Rectangle r) {
        if (r instanceof Rectangle(ColoredPoint(Point(var x, var y), var c),
                var lr)) {
            System.out.println("Upper-left corner: " + x);
        }
    }

    // record patterns with generic types
    record Box<T>(T t) {}
    void test1(Box<Object> bo) {
        if (bo instanceof Box<Object>(String s)) {
            System.out.println("String " + s);
        }
    }
    void test2(Box<String> bo) {
        if (bo instanceof Box<String>(var s)) {
            System.out.println("String " + s);
        }
    }
}