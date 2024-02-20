public class Chessboard {
    public enum Column {
        a,b,c,d,e,f,g,h;
        public static char getLetterForColumn(int columnNumber) {
            if (columnNumber >= 1 && columnNumber <= values().length) {
                return values()[columnNumber - 1].name().charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid Column Number");
            }
        }
    }

// changes?




}
