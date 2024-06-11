package es.ucm.tp1.view;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;

public class GameSerializer extends View {
	
	private static final String MSG = "---- ROAD FIGHTER SERIALIZED ----";
	
	Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		String o;
		str.append(getInfo());
		
		for(int i = 0; i < game.getRoadLength(); i++) { // Columna
			for(int j = 0; j < game.getRoadWidth(); j++) { // Fila
				 o = game.getObjectInfo(j, i);
				 if(!"".equals(o)) 
					 str.append(o + StringUtils.LINE_SEPARATOR);
			}
		}
		return str.toString();
	}

	@Override
	protected String getInfo() {
		StringBuilder buffer = new StringBuilder();
		
		/* @formatter:off */
		buffer
		
		.append(MSG).append(StringUtils.LINE_SEPARATOR)
		.append("Level: ").append(game.getLevel()).append(StringUtils.LINE_SEPARATOR)
		.append("Cycles: ").append(game.getCycle()).append(StringUtils.LINE_SEPARATOR)
		.append("Coins: ").append(game.playerCoins()).append(StringUtils.LINE_SEPARATOR);
		
		if (!game.isTestMode()) {
			buffer.append("EllapsedTime: ")
			.append(game.elapsedTime()).append(StringUtils.LINE_SEPARATOR);
		}
		
		buffer.append("Game Objects: ").append(StringUtils.LINE_SEPARATOR);

		return buffer.toString();
	}
}
