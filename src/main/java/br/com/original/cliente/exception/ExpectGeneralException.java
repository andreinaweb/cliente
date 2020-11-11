package br.com.original.cliente.exception;

public class ExpectGeneralException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void validateParameters(String param) {
		    if (param == null) {
		        throw new NullPointerException("Parâmetros nulos não são permitidos");
		    }
		}
	
}
