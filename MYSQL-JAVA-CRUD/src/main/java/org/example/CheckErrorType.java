package org.example;

public class CheckErrorType {
    public String CheckError(int errorCode) throws Exception {
        System.out.println(errorCode);
        String error;
        switch (errorCode) {
            case 1062:
                error = "Wrong Input - Duplicate Value";
                break;
            case 1366:
                error = "Incorrect Integer Value";
                break;
            case 1:
                error = "Class Not Found";
                break;
            case 2:
                error = "Json Data Error";
                break;
            case 3:
                error = "Wrong Input - Runtime Error";
                break;
            default:
                throw new Exception("Error");
        }
        return error;
    }
}