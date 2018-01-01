package com.comcast.coding.test.exception;

/**
 * @author likhithkumarmatta
 * 
 * Application level Checked Exception class
 *
 */
public class ExerciseRuntimeException extends RuntimeException{

    /**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 7306755120829282724L;

	public ExerciseRuntimeException() {
        super();
    }

    public ExerciseRuntimeException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
