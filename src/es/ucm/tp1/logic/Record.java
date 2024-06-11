package es.ucm.tp1.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.InputOutputRecordException;
import es.ucm.tp1.utils.StringUtils;

public class Record {
	
	private static final String LOAD_RECORD_ERROR = "An error occurred while loading the record";

	private static final String DEFAULT_RECORD_MSG = "Creating default record for level ";

	private static final String FILENAME = "record.txt";
	private Level level;
	private Long record;
	private boolean betterRecord = false;
	
	private Record(Level level, Long record) {
		this.level = level;
		this.record = record;
	}
	
	static Record loadRecord(Level gameLevel) throws InputOutputRecordException{

		Level level = null;
		Long recordAux = Long.MAX_VALUE;
		String levelString, recordString;
		
		try (BufferedReader inChars = new BufferedReader(new FileReader(FILENAME))){
			String l = inChars.readLine();
			
			while(l != null) {
				
				String[] parts = l.trim().split(":");
				levelString = parts[0];
				recordString = parts[1];
				
				// Comprobamos si el nivel es valido.
				level = Level.valueOfIgnoreCase(levelString);
				
				if(gameLevel == level)
					recordAux = Long.parseLong(recordString);
				
				l = inChars.readLine();
			}
			
			if(recordAux == Long.MAX_VALUE)
				System.out.println(DEFAULT_RECORD_MSG + "'" + gameLevel.name() + "'");
			
		} catch(NumberFormatException | IOException e) {
			throw new InputOutputRecordException(LOAD_RECORD_ERROR, e);
		}
		
		return new Record(gameLevel, recordAux);
	}
	
	private void saveRecord() throws InputOutputRecordException{
		
		StringBuilder buffer = new StringBuilder();
		
		try (BufferedReader inChars = new BufferedReader(new FileReader("record.txt"))){
			String l = inChars.readLine();
			
			while(l != null) {
				
				String[] parts = l.split(":");
				String levelString = parts[0];
				
				// Comprobamos si el nivel es valido (por ejemplo que no ponga hola).
				Level levelAux = Level.valueOfIgnoreCase(levelString);
				
				if(level != levelAux)
					buffer.append(l).append(StringUtils.LINE_SEPARATOR);
				
				l = inChars.readLine();
			}
			
			buffer.append(level.name()).append(":").append(record);
			
		} catch (IOException e) {
			throw new InputOutputRecordException(LOAD_RECORD_ERROR, e);
		}
		
		try (BufferedWriter outChars = new BufferedWriter(new FileWriter("record.txt"))) {
			outChars.write(buffer.toString());
		}
		
		catch(IOException e) {
			throw new InputOutputRecordException(LOAD_RECORD_ERROR, e);
		}
	}
	
	void setRecord(Long elapsedTime) throws InputOutputRecordException{
		if(elapsedTime < record) {
			betterRecord = true;
			record = elapsedTime;
			
			saveRecord();
		}
	}
	
	boolean isNewRecord() {
		return betterRecord;
	}

	long getCurrentRecord() {
		return record;
	}
}
