package es.ucm.tp1.view;

public abstract class View {

	public abstract String toString();
	protected abstract String getInfo();
	
	protected static String formatTime(long t) {
		return String.format("%.2f s", t / 1000.0);
	}
}