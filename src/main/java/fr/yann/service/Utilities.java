package fr.yann.service;

public class Utilities {

    /**
     * Three options:
     *  - < minute: represented as numeric value (24.58)
     *  - < hour: represented as string ('1:51.91')
     *  - >= hour: represented as date (Sun Dec 31 01:06:25 CET 1899)
     * @param cell
     * @return
     */
    public static Double parsePerformanceFromCell(String cell) throws NumberFormatException {

        if (isNumericCell(cell)) {
            return Double.parseDouble(cell);
        }

        // In all other cases, treat as string
        return parseTime(cell);
    }

    private static boolean isNumericCell(String cell) {
        return !cell.contains("m");
    }

    /**
     * Casts a performance to a double if possible
     * If time format, parses the time.
     * @param performance
     * @return
     * @throws NumberFormatException
     */
    public static Double parseTime(String performance) throws NumberFormatException {
        if (performance.contains(":")) {
            try {
                return parseTimeSemicolon(performance);
            } catch (Exception e) {
                throw new NumberFormatException( String.format("Can't process '%s'", performance) );
            }
        } else if (performance.matches(".*\\.\\d\\d\\..*")) {
            try {
                return parseTimeDot(performance);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new NumberFormatException( String.format("Can't process '%s'", performance) );
            }
        } else {
            throw new NumberFormatException( String.format("Can't process '%s' as it is not a recognised format", performance) );
        }
    }

    /**
     * Parses time in the format hh:mm:ss.tt
     * @param performance
     * @return
     * @throws NumberFormatException
     */
    public static Double parseTimeSemicolon(String performance) throws NumberFormatException {
        String[] parts = performance.split(":");
        int hours = 0, minutes = 0;
        Double seconds;
        switch(parts.length) {
            case 1:
                return Double.valueOf(performance);
            case 2:
                minutes = Integer.parseInt( parts[0] );
                seconds = Double.parseDouble( parts[1] );
                break;
            case 3:
                hours = Integer.parseInt( parts[0] );
                minutes = Integer.parseInt( parts[1] );
                seconds = Double.parseDouble( parts[2] );
                break;
            default:
                throw new NumberFormatException("Unexpected number of parts.");
        }
        return hours*3600 + minutes*60 + seconds;
    }

    /**
     * Parses time in the format [m]m.ss.t
     * @param performance
     * @return
     * @throws NumberFormatException
     */
    public static Double parseTimeDot(String performance) throws NumberFormatException {
        String[] parts = performance.split("\\.");
        int hours = 0, minutes = 0, seconds, tenths;
        switch(parts.length) {
            case 2:
                // seconds = Integer.parseInt( parts[0] );
                // tenths = Integer.parseInt( parts[1] );
                // break;
            	return Double.parseDouble(performance);
            case 3:
                minutes = Integer.parseInt( parts[0] );
                seconds = Integer.parseInt( parts[1] );
                tenths = Integer.parseInt( parts[2] );
                break;
            case 4:
                hours = Integer.parseInt( parts[0] );
                minutes = Integer.parseInt( parts[1] );
                seconds = Integer.parseInt( parts[2] );
                tenths = Integer.parseInt( parts[3] );
                break;
            default:
                throw new NumberFormatException("Unexpected number of parts.");
        }
        return hours*3600 + minutes*60 + seconds + tenths/10d;
    }

    public static Double parsePerfSautLancer(String perf){
        return Double.parseDouble(perf.replace("m", "."));
    }
    
    // 2.07.04 -> 127.04
	public static double getChronoSecondesFromPerf(String perf) {
        String[] parts = perf.split("\\.");
        int minutes = 0, seconds, tenths;
        switch(parts.length) {
            case 2:
                seconds = Integer.parseInt( parts[0] );
                tenths = Integer.parseInt( parts[1] );
                break;
            case 3:
                minutes = Integer.parseInt( parts[0] );
                seconds = Integer.parseInt( parts[1] );
                tenths = Integer.parseInt( parts[2] );
                break;
            default:
                throw new NumberFormatException("Unexpected number of parts.");
        }
        return minutes*60 + seconds + tenths/100d;
	}

	public static void main(String[] args) {
		System.out.println(getChronoSecondesFromPerf("2.07.04"));
	}
}
