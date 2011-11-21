package com.gemserk.commons.gdx.resources;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FontResourceBuilder implements ResourceBuilder<BitmapFont> {

	private final FileHandle imageFile;
	private final FileHandle fontFile;

	private TextureFilter minFilter = TextureFilter.Nearest;
	private TextureFilter magFilter = TextureFilter.Nearest;
	
	private boolean useIntegerPositions = true;
	private CharSequence fixedWidthGlyphs = null;

	public FontResourceBuilder filter(TextureFilter filter) {
		this.minFilter = filter;
		this.magFilter = filter;
		return this;
	}
	
	public FontResourceBuilder minFilter(TextureFilter minFilter) {
		this.minFilter = minFilter;
		return this;
	}

	public FontResourceBuilder magFilter(TextureFilter magFilter) {
		this.magFilter = magFilter;
		return this;
	}

	public FontResourceBuilder useIntegerPositions(boolean useIntegerPositions) {
		this.useIntegerPositions = useIntegerPositions;
		return this;
	}

	public FontResourceBuilder fixedWidthGlyphs(CharSequence fixedWidthGlyphs) {
		this.fixedWidthGlyphs = fixedWidthGlyphs;
		return this;
	}

	@Override
	public boolean isVolatile() {
		return true;
	}

	@Override
	public BitmapFont build() {
		Texture texture = new Texture(imageFile);
		texture.setFilter(minFilter, magFilter);
		BitmapFont bitmapFont = new BitmapFont(fontFile, new Sprite(texture), false);
		bitmapFont.setUseIntegerPositions(useIntegerPositions);
		if (fixedWidthGlyphs != null)
			bitmapFont.setFixedWidthGlyphs(fixedWidthGlyphs);
		return bitmapFont;
	}

	public FontResourceBuilder(FileHandle imageFile, FileHandle fontFile) {
		this.imageFile = imageFile;
		this.fontFile = fontFile;
	}

}