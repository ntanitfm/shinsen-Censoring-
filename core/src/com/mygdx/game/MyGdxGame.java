package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.title.TitleScreen;

public class MyGdxGame extends Game {
	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}
}
