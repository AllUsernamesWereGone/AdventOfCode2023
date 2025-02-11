package Days;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public final class PipeMaze {
    record PipeSegment(boolean connectsN, boolean connectsE, boolean connectsS, boolean connectsW) {
    }

    private static final PipeSegment OUTSIDE_PIPE_SEGMENT = new PipeSegment(false, false, false, false);

    record MazeCoordinate(int x, int y) {
    }

    private static final MazeCoordinate INVALID_MAZE_COORDINATE = new MazeCoordinate(-1, -1);

    private final Map<MazeCoordinate, PipeSegment> maze;
    private final int width;
    private final int height;
    private MazeCoordinate startCoordinate = INVALID_MAZE_COORDINATE;

    public PipeMaze(final int width, final int height, final MazeCoordinate startCoordinate, final Map<MazeCoordinate, PipeSegment> maze) {
        this.width = width;
        this.height = height;
        this.startCoordinate = startCoordinate;
        this.maze = maze;
    }

    public PipeMaze eliminateAllButPipeLoop()
    {
        final Map<MazeCoordinate, PipeSegment> newMaze = new HashMap<>();
        MazeCoordinate path1 = INVALID_MAZE_COORDINATE;

        if (pipeConnectsNorth(startCoordinate.x, startCoordinate.y))
        {
            path1 = new MazeCoordinate(startCoordinate.x, startCoordinate.y-1);
        }
        else
        {
            if (pipeConnectsEast(startCoordinate.x, startCoordinate.y))
            {
                path1 = new MazeCoordinate(startCoordinate.x+1, startCoordinate.y);
            }
            else
            {
                path1 = new MazeCoordinate(startCoordinate.x, startCoordinate.y+1);
            }
        }
        MazeCoordinate path1prev = startCoordinate;

        newMaze.put(startCoordinate, maze.get(startCoordinate));
        while (!path1.equals(startCoordinate))
        {
            newMaze.put(path1, maze.get(path1));
            final MazeCoordinate path1New = navigateOneStep(path1, path1prev);
            path1prev = path1;
            path1 = path1New;
        }

        return new PipeMaze(width, height, startCoordinate, newMaze);
    }

    public void FillOutsideLocations()
    {
        for (int x=0; x<width; x++)
        {
            if (coordinateIsUnfilled(x,0))  floodFill(x,0);
            if (coordinateIsUnfilled(x,height-1))  floodFill(x,height-1);
        }
        for (int y=0; y<height; y++)
        {
            if (coordinateIsUnfilled(0,y))  floodFill(0,y);
            if (coordinateIsUnfilled(width-1,y))  floodFill(width-1,y);
        }
    }

    public Long CountInsideLocations()
    {
        long count = 0;
        for (int y=0; y<height; y++)
        {
            for (int x=0; x<width-1; x++)
            {
                if (coordinateIsUnfilled(x,y)) count++;
            }
        }

        return count;
    }

    private final Deque<MazeCoordinate> stack = new ArrayDeque<>();
    private void floodFill(final int xIn, final int yIn)
    {
        stack.push(new MazeCoordinate(xIn, yIn));
        while (!stack.isEmpty())
        {
            final MazeCoordinate curCoord = stack.pop();
            maze.put(curCoord, OUTSIDE_PIPE_SEGMENT);

            final int x = curCoord.x;
            final int y = curCoord.y;
            if (coordinateIsUnfilled(x-1,y)) stack.push(new MazeCoordinate(x-1, y));
            if (coordinateIsUnfilled(x+1,y)) stack.push(new MazeCoordinate(x+1, y));
            if (coordinateIsUnfilled(x,y-1)) stack.push(new MazeCoordinate(x, y-1));
            if (coordinateIsUnfilled(x,y+1)) stack.push(new MazeCoordinate(x, y+1));
        }
    }

    private boolean coordinateIsUnfilled(final int x, final int y)
    {
        if ( (x<0) || (x>=width) || (y<0) || (y>height-1)) return false;

        final MazeCoordinate mc = new MazeCoordinate(x, y);
        if (maze.containsKey(mc))
        {
            final PipeSegment ps = maze.get(mc);
            if (ps == OUTSIDE_PIPE_SEGMENT)  return false;
            if (ps.connectsN || ps.connectsE || ps.connectsS || ps.connectsW) return false;
        }

        return true;
    }

    public PipeMaze doubleInSize()
    {
        final Map<MazeCoordinate, PipeSegment> newMaze = new HashMap<>();
        for (int y=0; y<height; y++)
        {
            for (int x=0; x<width; x++)
            {
                final MazeCoordinate origMC = new MazeCoordinate(x, y);
                if (maze.containsKey(origMC))
                {
                    final PipeSegment ps = maze.get(origMC);
                    final MazeCoordinate mc = new MazeCoordinate(x*2, y*2);
                    newMaze.put(mc, ps);
                    if (ps.connectsE)
                    {
                        newMaze.put(new MazeCoordinate(mc.x+1, mc.y), new PipeSegment(false, true, false, true));
                    }
                    if (ps.connectsS)
                    {
                        newMaze.put(new MazeCoordinate(mc.x, mc.y+1), new PipeSegment(true, false, true, false));
                    }
                }
            }
        }

        final MazeCoordinate newStartCoordinate = new MazeCoordinate(startCoordinate.x*2, startCoordinate.y*2);
        return new PipeMaze(width*2, height*2, newStartCoordinate, newMaze);
    }

    public PipeMaze halveInSize()
    {
        final Map<MazeCoordinate, PipeSegment> newMaze = new HashMap<>();
        for (int y=0; y<height; y+=2)
        {
            for (int x=0; x<width; x+=2)
            {
                final MazeCoordinate origMC = new MazeCoordinate(x, y);
                if (maze.containsKey(origMC))
                {
                    final PipeSegment ps = maze.get(origMC);
                    final MazeCoordinate mc = new MazeCoordinate(x/2, y/2);
                    newMaze.put(mc, ps);
                }
            }
        }

        final MazeCoordinate newStartCoordinate = new MazeCoordinate(startCoordinate.x/2, startCoordinate.y/2);
        return new PipeMaze(width/2, height/2, newStartCoordinate, newMaze);
    }

    public PipeMaze(final String[] day10InputLines) {
        maze = new HashMap<>();
        width = day10InputLines[0].length();
        height = day10InputLines.length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final MazeCoordinate mc = new MazeCoordinate(x, y);
                final char c = day10InputLines[y].charAt(x);
                switch (c) {
                    case 'S': {
                        startCoordinate = mc;
                    }
                    break;
                    case '|': {
                        maze.put(mc, new PipeSegment(true, false, true, false));
                    }
                    break;
                    case '-': {
                        maze.put(mc, new PipeSegment(false, true, false, true));
                    }
                    break;
                    case 'L': {
                        maze.put(mc, new PipeSegment(true, true, false, false));
                    }
                    break;
                    case 'J': {
                        maze.put(mc, new PipeSegment(true, false, false, true));
                    }
                    break;
                    case '7': {
                        maze.put(mc, new PipeSegment(false, false, true, true));
                    }
                    break;
                    case 'F': {
                        maze.put(mc, new PipeSegment(false, true, true, false));
                    }
                    break;
                    case '.': {
                        // empty ground, do nothing
                    }
                    break;
                    default: {
                        throw new IllegalStateException("Unexpected character: " + c);
                    }
                }
            }
        }
        connectStart();
    }

    private void connectStart() {
        boolean n = false;
        boolean e = false;
        boolean s = false;
        boolean w = false;

        if (pipeConnectsSouth(startCoordinate.x, startCoordinate.y - 1)) n = true;
        if (pipeConnectsWest(startCoordinate.x + 1, startCoordinate.y)) e = true;
        if (pipeConnectsNorth(startCoordinate.x, startCoordinate.y + 1)) s = true;
        if (pipeConnectsEast(startCoordinate.x - 1, startCoordinate.y)) w = true;

        maze.put(startCoordinate, new PipeSegment(n, e, s, w));
    }

    private boolean pipeConnectsSouth(final int x, final int y) {
        final MazeCoordinate mc = new MazeCoordinate(x, y);
        if (maze.containsKey(mc)) {
            final PipeSegment ps = maze.get(mc);
            if (ps.connectsS) return true;
        }
        return false;
    }

    private boolean pipeConnectsNorth(final int x, final int y) {
        final MazeCoordinate mc = new MazeCoordinate(x, y);
        if (maze.containsKey(mc)) {
            final PipeSegment ps = maze.get(mc);
            if (ps.connectsN) return true;
        }
        return false;
    }

    private boolean pipeConnectsEast(final int x, final int y) {
        final MazeCoordinate mc = new MazeCoordinate(x, y);
        if (maze.containsKey(mc)) {
            final PipeSegment ps = maze.get(mc);
            if (ps.connectsE) return true;
        }
        return false;
    }

    private boolean pipeConnectsWest(final int x, final int y) {
        final MazeCoordinate mc = new MazeCoordinate(x, y);
        if (maze.containsKey(mc)) {
            final PipeSegment ps = maze.get(mc);
            if (ps.connectsW) return true;
        }
        return false;
    }

    // I originally wrote this to walk two different paths until they met.  I had a bug that was allowing that
    // to get into an infinite loop.  Instead I decided to code it to walk the whole loop and then cut the distance
    // of the whole loop in half.  I left the other code present but commented out for posterity.
    public Long findLengthToMidpoint() {
        MazeCoordinate path1 = INVALID_MAZE_COORDINATE;
        MazeCoordinate path2 = INVALID_MAZE_COORDINATE;

        if (pipeConnectsNorth(startCoordinate.x, startCoordinate.y)) {
            path1 = new MazeCoordinate(startCoordinate.x, startCoordinate.y - 1);
            if (pipeConnectsEast(startCoordinate.x, startCoordinate.y))
                path2 = new MazeCoordinate(startCoordinate.x + 1, startCoordinate.y);
            if (pipeConnectsSouth(startCoordinate.x, startCoordinate.y))
                path2 = new MazeCoordinate(startCoordinate.x, startCoordinate.y + 1);
            if (pipeConnectsWest(startCoordinate.x, startCoordinate.y))
                path2 = new MazeCoordinate(startCoordinate.x - 1, startCoordinate.y);
        } else {
            if (pipeConnectsEast(startCoordinate.x, startCoordinate.y)) {
                path1 = new MazeCoordinate(startCoordinate.x + 1, startCoordinate.y);
                if (pipeConnectsSouth(startCoordinate.x, startCoordinate.y))
                    path2 = new MazeCoordinate(startCoordinate.x, startCoordinate.y + 1);
                if (pipeConnectsWest(startCoordinate.x, startCoordinate.y))
                    path2 = new MazeCoordinate(startCoordinate.x - 1, startCoordinate.y);
            } else {
                path1 = new MazeCoordinate(startCoordinate.x, startCoordinate.y + 1);
                path2 = new MazeCoordinate(startCoordinate.x - 1, startCoordinate.y);
            }
        }
        MazeCoordinate path1prev = startCoordinate;
        MazeCoordinate path2prev = startCoordinate;

        long result = 1;
        while (!path1.equals(startCoordinate)) {
            // System.out.println("" + result + " " + path1.toString() + " " + path2.toString());
            final MazeCoordinate path1New = navigateOneStep(path1, path1prev);
            // final MazeCoordinate path2New = navigateOneStep(path2, path2prev);
            path1prev = path1;
            // path2prev = path2;
            path1 = path1New;
            // path2 = path2New;
            result++;
        }

        return (result + 1) / 2;
    }

    private MazeCoordinate navigateOneStep(final MazeCoordinate curCoord, final MazeCoordinate prevCoord) {
        if (prevCoord.y == curCoord.y) {
            if (prevCoord.x + 1 == curCoord.x) {
                if (pipeConnectsNorth(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x, curCoord.y - 1);
                if (pipeConnectsEast(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x + 1, curCoord.y);
                if (pipeConnectsSouth(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x, curCoord.y + 1);
            }
            if (pipeConnectsNorth(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x, curCoord.y - 1);
            if (pipeConnectsSouth(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x, curCoord.y + 1);
            if (pipeConnectsWest(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x - 1, curCoord.y);
        }

        if (prevCoord.y + 1 == curCoord.y) {
            if (pipeConnectsEast(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x + 1, curCoord.y);
            if (pipeConnectsSouth(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x, curCoord.y + 1);
            if (pipeConnectsWest(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x - 1, curCoord.y);
        }
        if (pipeConnectsNorth(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x, curCoord.y - 1);
        if (pipeConnectsEast(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x + 1, curCoord.y);
        if (pipeConnectsWest(curCoord.x, curCoord.y)) return new MazeCoordinate(curCoord.x - 1, curCoord.y);

        throw new IllegalStateException("Couldn't determine direction at: " + curCoord + " coming from " + prevCoord);
    }
}