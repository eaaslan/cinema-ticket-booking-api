class ArrayOperations {
    public static void printCorners(int[][] twoDimArray) {
        int rows = twoDimArray.length;
        int cols = twoDimArray[0].length;

        // Top left corner
        int topLeft = twoDimArray[0][0];
        // Top right corner
        int topRight = twoDimArray[0][cols - 1];
        // Bottom left corner
        int bottomLeft = twoDimArray[rows - 1][0];
        // Bottom right corner
        int bottomRight = twoDimArray[rows - 1][cols - 1];

        // Print top row corners
        System.out.println(topLeft + " " + topRight);
        // Print bottom row corners
        System.out.println(bottomLeft + " " + bottomRight);
    }
}