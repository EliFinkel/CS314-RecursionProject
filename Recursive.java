/*  Student information for assignment:
 *
 *  On <MY|OUR> honor, Eli Finkel and Gabe Aguilar,
 *  this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
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
 *  UTEID:
 *  email address:
 *
 */

//imports

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.
     * <br>
     * pre: n != Integer.MIN_VALUE<br>
     * <br>
     * post: Returns a String that represents N in binary.
     * All chars in returned String are '1's or '0's.
     * Most significant digit is at position 0
     * 
     * @param n the base 10 int to convert to base 2
     * @return a String that is a binary representation of the parameter n
     */
    public static String getBinary(int n) {
        if (n == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "getBinary. n cannot equal "
                    + "Integer.MIN_VALUE. n: " + n);
        }

        if (n == 0) {
            // first base case: last number's quotient is 0
            return "0";
        } else if (n == 1) {
            // second base case: last number's quotient is 1
            return "1";
        } else if (n < 0) {
            // third base case: last number's quotient is negative
            return "-" + getBinary(-n);
        } else {
            // divide the number by 2 to get the binary representation and append the
            // remainder
            return getBinary(n / 2) + Math.abs(n % 2);
        }
    }

    /**
     * Problem 2: reverse a String recursively.<br>
     * pre: stringToRev != null<br>
     * post: returns a String that is the reverse of stringToRev
     * 
     * @param stringToRev the String to reverse.
     * @return a String with the characters in stringToRev
     *         in reverse order.
     */
    public static String revString(String stringToRev) {
        if (stringToRev == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        // Base case
        if (stringToRev.length() <= 1) {
            return stringToRev;
        }

        // Move the char in the beggining of the stringToRev to the back recursively
        return revString(stringToRev.substring(1)) + stringToRev.charAt(0);
    }

    /**
     * Problem 3: Returns the number of elements in data
     * that are followed directly by value that is
     * double that element.
     * pre: data != null
     * post: return the number of elements in data
     * that are followed immediately by double the value
     * 
     * @param data The array to search.
     * @return The number of elements in data that
     *         are followed immediately by a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        // Call recursive helper method
        return checkNextIsDouble(data, 0);
    }

    // CS314 students, add your nextIsDouble helper method here

    public static int checkNextIsDouble(int[] data, int index) {
        if (index >= data.length - 1) {
            return 0;
        }
        // Check if element at index is followed by its double
        int count = data[index] * 2 == data[index + 1] ? 1 : 0;

        // Add count to a recursive call with the next index
        return count + checkNextIsDouble(data, index + 1);

    }

    /**
     * Problem 4: Find all combinations of mnemonics
     * for the given number.<br>
     * pre: number != null, number.length() > 0,
     * all characters in number are digits<br>
     * post: see tips section of assignment handout
     * 
     * @param number The number to find mnemonics for
     * @return An ArrayList<String> with all possible mnemonics
     *         for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null || number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }

        // To store the mnemonics
        ArrayList<String> results = new ArrayList<>();
        // Call helper method
        recursiveMnemonics(results, "", number);
        return results;
    }

    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a partial (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used
     * from the original number.
     */
    private static void recursiveMnemonics(ArrayList<String> result,
            String mnemonicSoFar, String digitsLeft) {
        if (digitsLeft.isEmpty()) {
            // If no more digits left add mnemonicSoFar
            result.add(mnemonicSoFar);
        } else {
            // Get the letters from the current digit
            String letters = digitLetters(digitsLeft.charAt(0));

            // Go through each letter and do a recursive call with the letter added to
            // mnemonicSoFar
            for (int i = 0; i < letters.length(); i++) {
                recursiveMnemonics(result, mnemonicSoFar +
                        letters.charAt(i), digitsLeft.substring(1));
            }
        }

    }

    /*
     * Static code blocks are run once when this class is loaded.
     * Here we create an unmoddifiable list to use with the phone
     * mnemonics method.
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = { "0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);

    }
    // used by method digitLetters

    /*
     * helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with
     * this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }

    /*
     * helper method for listMnemonics
     * pre: s != null
     * post: return true if every character in s is
     * a digit ('0' through '9')
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 5: Draw a Sierpinski Carpet.
     * 
     * @param size  the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /*
     * Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * 
     * @param g The Graphics object to use to fill rectangles
     * 
     * @param size the size of the current square
     * 
     * @param limit the smallest allowable size of squares
     * 
     * @param x the x coordinate of the upper left corner of the current square
     * 
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit,
            double x, double y) {

        if (size <= limit) {
            return; // Base case: if the square is too small, do not draw it
        }

        // Calculate the size of the new squares
        int newSize = size / 3;
        // Cast double values to ints
        int xInt = (int) x;
        int yInt = (int) y;
        // Create a new central rect
        g.fillRect(xInt + newSize, yInt + newSize, newSize, newSize);

        // Loop through each of the 9 other sqaures sourounding the central square
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                // Avoid the center square
                if (!(i == 1 && j == 1)) {
                    // Draw each new square
                    drawSquares(g, newSize, limit, x + i * newSize, y + j * newSize);
                }
        }
    }

    /**
     * Problem 6: Determine if water at a given point
     * on a map can flow off the map.
     * <br>
     * pre: map != null, map.length > 0,
     * map is a rectangular matrix, 0 <= row < map.length,
     * 0 <= col < map[0].length
     * <br>
     * post: return true if a drop of water starting at the location
     * specified by row, column can reach the edge of the map,
     * false otherwise.
     * 
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return return true if a drop of water starting at the location
     *         specified by row, column can reach the edge of the map, false
     *         otherwise.
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "canFlowOffMap");
        }
        // Base case to check edges
        if (row == 0 || row == map.length - 1 || col == 0 || col == map[0].length - 1) {
            return true;
        }
        // Store val
        int currentVal = map[row][col];
        boolean canFlow = false;

        // Temporarily mark the current cell to avoid infinite recursion
        map[row][col] = Integer.MAX_VALUE;
        // Check all four directions for a smaller value, and recurse if found
        canFlow = checkDirections(map, row, col, currentVal, canFlow);

        // Backtrack
        map[row][col] = currentVal;

        return canFlow;
    }

    private static boolean checkDirections(int[][] map, int row, int col, int currentVal, boolean canFlow) {
        if (row < map.length - 1 && map[row + 1][col] < currentVal) {
            if (canFlowOffMap(map, row + 1, col)) {
                canFlow = true;
            }
        }
        if (row > 0 && map[row - 1][col] < currentVal) {
            if (canFlowOffMap(map, row - 1, col)) {
                canFlow = true;
            }
        }
        if (col < map[0].length - 1 && map[row][col + 1] < currentVal) {
            if (canFlowOffMap(map, row, col + 1)) {
                canFlow = true;
            }
        }
        if (col > 0 && map[row][col - 1] < currentVal) {
            if (canFlowOffMap(map, row, col - 1)) {
                canFlow = true;
            }
        }
        return canFlow;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null,
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * helper method for canFlowOfMap - CS314 students you should not have to
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 7: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     * <br>
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br>
     * post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability.
     * 
     * @param numTeams  the number of teams to form.
     *                  Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team
     *         with the maximum total ability and the team with the minimum total
     *         ability. The return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        // Store each teams total score
        int[] teamScores = new int[numTeams];

        // Store number of members on each team
        int[] teamMembers = new int[numTeams];

        // Call the recursive helper method
        return minDiffHelper(abilities, 0, teamScores, teamMembers, Integer.MAX_VALUE, 0);
    }

    public static int minDiffHelper(int[] abilities, int index, int[] teamScores,
            int[] teamMembers, int minDiff, int filledTeams) {

        if (index == abilities.length) {
            // Check if all teams have at least one member
            if (filledTeams == teamScores.length) {
                int minScore = Integer.MAX_VALUE;
                int maxScore = Integer.MIN_VALUE;

                // Find the minimum and maximum team scores
                for (int score : teamScores) {
                    minScore = Math.min(minScore, score);
                    maxScore = Math.max(maxScore, score);
                }

                // Update minDiff with the smallest difference encountered
                return Math.min(minDiff, maxScore - minScore);
            } else {
                // Return maximum value if not all teams have members
                return Integer.MAX_VALUE;
            }
        }

        int currentMinDiff = minDiffRecursiveCall(abilities, index, teamScores, teamMembers, minDiff, filledTeams);
        return currentMinDiff;

    }

    private static int minDiffRecursiveCall(int[] abilities, int index, int[] teamScores, int[] teamMembers,
            int minDiff,
            int filledTeams) {
        // Temporarily set currentMinDiff to MAX
        int currentMinDiff = Integer.MAX_VALUE;

        // Loop through each team
        for (int i = 0; i < teamScores.length; i++) {
            teamScores[i] += abilities[index];
            teamMembers[i]++;

            int newFilledTeams = filledTeams + (teamMembers[i] == 1 ? 1 : 0);
            // Recursive call
            int diff = minDiffHelper(abilities, index + 1, teamScores,
                    teamMembers, minDiff, newFilledTeams);
            // Find smallest minDiff
            currentMinDiff = Math.min(currentMinDiff, diff);

            // Backtrack
            teamScores[i] -= abilities[index];
            teamMembers[i]--;

        }
        return currentMinDiff;
    }

    /**
     * Problem 8: Maze solver.
     * <br>
     * pre: board != null
     * <br>
     * pre: board is a rectangular matrix
     * <br>
     * pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
     * <br>
     * pre: there is a single 'S' character present
     * <br>
     * post: rawMaze is not altered as a result of this method.
     * Return 2 if it is possible to escape the maze after
     * collecting all the coins.
     * Return 1 if it is possible to escape the maze
     * but without collecting all the coins.
     * Return 0 if it is not possible
     * to escape the maze. More details in the assignment handout.
     * 
     * @param rawMaze represents the maze we want to escape.
     *                rawMaze is not altered as a result of this method.
     * @return per the post condition
     */
    public static int canEscapeMaze(char[][] rawMaze) {

        // Find row and col index of start
        int coinCount = 0;
        int startRow = 0;
        int startCol = 0;
        for (int row = 0; row < rawMaze.length; row++) {
            for (int col = 0; col < rawMaze[0].length; col++) {
                // Find total number of coins
                if (rawMaze[row][col] == '$') {
                    coinCount++;
                } else if (rawMaze[row][col] == 'S') {
                    startRow = row;
                    startCol = col;
                }
            }
        }

        // Call recursive helper method
        return canEscapeHelper(rawMaze, startRow, startCol, 0, coinCount);
    }

    private static int canEscapeHelper(char[][] rawMaze, int row, int col,
            int coinsCollected, int totalCoins) {
        // failure base
        if (row < 0 || row >= rawMaze.length || col < 0 || col >= rawMaze[0].length ||
                rawMaze[row][col] == '*') {
            return -1;
        }
        char currentVal = rawMaze[row][col];
        // Success Base case
        if (currentVal == 'E') {
            return coinsCollected == totalCoins ? 2 : 1;
        }
        // Update char at row and col as we visit it
        if (currentVal == '$') {
            coinsCollected++;
            rawMaze[row][col] = 'Y';
        } else if (currentVal == 'G' || currentVal == 'S') {
            rawMaze[row][col] = 'Y';
        } else if (currentVal == 'Y') {
            rawMaze[row][col] = '*';
        }
        int result = checkDirections(rawMaze, row, col, coinsCollected, totalCoins);
        // Backtrack
        rawMaze[row][col] = currentVal;
        return result;
    }

    private static int checkDirections(char[][] rawMaze, int row, int col,
            int coinsCollected, int totalCoins) {
        int result = 0;
        // Offsets to move up down left and right
        int[] rowOffsets = { -1, 1, 0, 0 };
        int[] colOffsets = { 0, 0, -1, 1 };

        // Loop through the four directions
        for (int i = 0; i < 4; i++) {
            // Recurive call
            int nextResult = canEscapeHelper(rawMaze, row + rowOffsets[i], col + colOffsets[i],
                    coinsCollected, totalCoins);
            if (nextResult == 2) {
                // Found a path with all coins collected
                return 2;
            } else if (nextResult == 1) {
                // Found a path but not all coins collected
                // Dont immidetly return in case another path works and collects all coins
                result = 1;
            }
        }
        return result;
    }
}