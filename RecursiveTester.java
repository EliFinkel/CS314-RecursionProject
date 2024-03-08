/*  Student information for assignment:
 *
 *  On our honor, Eli Finkel and Gabriel Aguilar, this programming assignment is our own work
 *  and we have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: egf525
 *  email address: eligfinkel@utexas.edu
 *  Grader name: Gracelynn Ray
 *  Section number: 50815
 *
 *  Student 2
 *  UTEID: gaa2292
 *  email address: gaa2292@utexas.edu
 *
 */

import java.util.ArrayList;
import java.util.Collections;
 
 /**
  * Tester class for the methods in Recursive.java
  * @author scottm
  *
  */
public class RecursiveTester {
 
    // run the tests
    public static void main(String[] args) {
        studentTests();
    }
 
    // post: run student test
    private static void studentTests() {
        doBinaryTests(93, 1011101, 1);
        doBinaryTests(44, 101100, 2);
        doBinaryTests(16, 10000, 3);
        doReverseTests("eligabriel", "leirbagile", 4);
        doReverseTests("cs314", "413sc", 5);
        doReverseTests("austin", "nitsua", 6);
        doNextIsDoubleTests(new int[] {44, 88, 22, 55, 22, 44, 88}, 3, 7);
        doNextIsDoubleTests(new int[] {47, 22, 10, 555, 33, -2}, 0, 8);
        doNextIsDoubleTests(new int[] {-2, -4, -8, 0, 0, 2, 3, 6, 12}, 5, 9);
        doListMnemonicsTests();
        doFlowOffMapTests(13);
        doFairTeamsTests(new int[] {700, 600, 500, 400, 300, 200, 100}, 3, 100, 15);
        doFairTeamsTests(new int[] {138, 99, 274, 438, 98, 111, 84, 332, 104}, 4, 26, 16);
        doFairTeamsTests(new int[] {22, 5, 25, 8, 12}, 3, 3, 17); 
        doMazeTests("$*ES$GGY*$", 1, 1, 18);
        doMazeTests("$$$$$$E", 1, 2, 19);
        doMazeTests("*GSYE$$*GS", 1, 0, 20);
        //add 2 carpet tests
    }

    private static void doBinaryTests(int num, int binary, int test) {
        String actualBinary = Recursive.getBinary(num);
        String expectedBinary = binary + "";
        if (actualBinary.equals(expectedBinary)) {
            System.out.println("Test " + test + " passed. Get binary.");
        } else {
            System.out.println("Test " + test + " FAILED. Get binary. expected: "
                    + expectedBinary + ", Actual result: " + actualBinary);
        }
    }

    private static void doReverseTests(String string, String reverse, int test) {
        String actualRev = Recursive.revString(string);
        String expectedRev = reverse;
        if (actualRev.equals(expectedRev)) {
            System.out.println("Test " + test + " passed. Reverse string.");
        } else {
            System.out.println("Test " + test + " FAILED. Reverse string. expected: " +
                    expectedRev + ", Actual result: " + actualRev);
        }
    }

    private static void doNextIsDoubleTests(int[] num, int expected, int test) {
        int[] numsForDouble = num;
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = expected;
        if (actualDouble == expectedDouble) {
            System.out.println("Test " + test + " passed. Next is double.");
        } else {
            System.out.println("Test " + test + " FAILED. Next is double. expected: "
                    + expectedDouble + ", Actual result: " + actualDouble);
        }
    }

    private static void doListMnemonicsTests() {
        ArrayList<String> mnemonics = Recursive.listMnemonics("32");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("DA");
        expected.add("DB");
        expected.add("DC");
        expected.add("EA");
        expected.add("EB");
        expected.add("EC");
        expected.add("FA");
        expected.add("FB");
        expected.add("FC");

        if (mnemonics.equals(expected)) {
            System.out.println("Test 10 passed. Phone mnemonics.");
        } else {
            System.out.println("Test 10 FAILED. Phone mnemonics.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result: " + mnemonics);
        }

        mnemonics = Recursive.listMnemonics("85");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("TJ");
        expected.add("TK");
        expected.add("TL");
        expected.add("UJ");
        expected.add("UK");
        expected.add("UL");
        expected.add("VJ");
        expected.add("VK");
        expected.add("VL");

        Collections.sort(expected);
        if (mnemonics.equals(expected)) {
            System.out.println("Test 11 passed. Phone mnemonics.");
        } else {
            System.out.println("Test 11 FAILED. Phone mnemonics.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result: " + mnemonics);
        }

        mnemonics = Recursive.listMnemonics("46");
        expected.clear();
        expected.add("GM");
        expected.add("GN");
        expected.add("GO");
        expected.add("HM");
        expected.add("HN");
        expected.add("HO");
        expected.add("IM");
        expected.add("IN");
        expected.add("IO");
        if (mnemonics.equals(expected)) {
            System.out.println("Test 12 passed. Phone mnemonics.");
        } else {
            System.out.println("Test 12 FAILED. Phone mnemonics.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result: " + mnemonics);
        }
    }

    private static void doFlowOffMapTests(int test) {
        int[][] world = {{0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 0},
                        {0, 1, 1000, 1, 0},
                        {0, 1, 1, 1, 0},
                        {0, 0, 0, 0, 0}};

        doOneFlowTest(world, 2, 2, true, test);

        world = new int[][]
                        {{1, 49, 36, 55, 19, 12},
                        {294, 7, 11, 395, 7, 30},
                        {35, 190, 55, 92, 3, 55},
                        {55, 4, 4, 9, 55, 35},
                        {11, 2, 889, 55, 28, 385},
                        {99, 402, 99, 33, 4, 11},
                        {230, 390, 9, 8, 44, 27}};

        doOneFlowTest(world, 4, 0, true, 14);
    }

    private static void doOneFlowTest(int[][] world, int r, int c,
    boolean expected, int test) {
        boolean actual = Recursive.canFlowOffMap(world, r, c);
        if (expected == actual) {
            System.out.println("Test " + test + " passed. Can flow off map.");
        } else {
            System.out.println("Test " + test + " FAILED. Can flow off map.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
    }

    private static void doFairTeamsTests(int[] abilities, int teams, int expected, int test) {
        int actual = Recursive.minDifference(teams, abilities);
        if (actual == expected) {
            System.out.println("Test " + test + " passed. Min difference.");
        } else {
            System.out.println("Test " + test + " FAILED. Min difference.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result: " + actual);
        }
    }

    private static void doMazeTests(String rawMaze, int rows, int expected, int test) {
        char[][] maze = makeMaze(rawMaze, rows);
        printMaze(maze);
        int actual = Recursive.canEscapeMaze(maze);
        if (expected == actual) {
            System.out.println("Test " + test + " passed. MazeSolver.");
        } else {
            System.out.println("Test " + test + " FAILED. MazeSolver.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result: " + actual);
        }
        System.out.println();
    }

    // print the given maze
    // pre: none
    private static void printMaze(char[][] maze) {
        if (maze == null) {
            System.out.println("NO MAZE GIVEN");
        } else {
            for (char[] row : maze) {
                for (char c : row) {
                    System.out.print(c);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    // generate a char[][] given the raw string
    // pre: rawMaze != null, rawMaze.length() % rows == 0
    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                    + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

 }