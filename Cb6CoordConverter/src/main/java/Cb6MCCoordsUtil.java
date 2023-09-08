/**
 * makes converting minecraft coords easier
 */
public class Cb6MCCoordsUtil {
    
    /**
     * 
     * @param raw takes a raw string of coords
     * @return
     * @throws IllegalArgumentException 
     */
    public static int[] cleanCoords(String raw) throws IllegalArgumentException
    {
        int coords[] = new int[2];
        char cc; // the current char
        String parsedCoords = "";
        String cleanCoords[] ;
        int spaces = 0;
        boolean numFound = false;
        
        if (raw.contains("X: ")) {
            raw = raw.replaceAll("X: ", "");
            raw = raw.replaceAll("Y: ", "");
            raw = raw.replaceAll("Z: ", "");
        }

        for (int i = 0; i < raw.length(); i++) {
            cc = raw.charAt(i);
            if (cc == '.') {
                throw new IllegalArgumentException("Decimal coords are not allowed.");
            }
            if (Character.isDigit(cc) || cc == '-') {
                parsedCoords += cc;
                numFound = true;
            }
            else if (cc == ' ') {
                spaces++;
                parsedCoords += cc;
                
            }
        }
        cleanCoords = parsedCoords.split(" ");
        
        if (spaces == 1) {
            coords[0] = Integer.parseInt(cleanCoords[0]);
            coords[1] = Integer.parseInt(cleanCoords[1]);
        }
        else if (spaces == 2) {
            coords[0] = Integer.parseInt(cleanCoords[0]);
            coords[1] = Integer.parseInt(cleanCoords[2]);
        }
        return coords;
    }
    
    /**
     * Converts overworld coords to nether coords
     * @param owCoords the overworld coords
     * @return the coords in the nether
     */
    public static int[] getNetherCoords(int[] owCoords) {
        int netherCoords[] = new int[2];
        
        netherCoords[0] = owCoords[0] / 8;
        netherCoords[1] = owCoords[1] / 8;
        
        return netherCoords;
    }
    
    /**
     * Converts nether coords to overworld coords
     * @param nCoords the nether coords
     * @return the coords in overworld
     */
    public static int[] getOverworldCoords(int[] nCoords) {
        int owCoords[] = new int[2];
        
        owCoords[0] = nCoords[0] * 8;
        owCoords[1] = nCoords[1] * 8;
        
        return owCoords;
    }
}
