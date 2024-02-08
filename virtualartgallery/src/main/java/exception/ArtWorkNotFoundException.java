package exception;

public class ArtWorkNotFoundException extends Exception {
   

	public ArtWorkNotFoundException(String message) {
        super(message);
        
        
        //UFA DAO line 34
    }
	
	
	
	 @Override
		public String toString() {
			return "ArtWorkNotFoundException";
		}
}
