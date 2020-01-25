package com.koenigkatze.asoulforasoul.media.animations;

import com.koenigkatze.asoulforasoul.constants.FileConstants;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public final class AnimationResourcesLoader
{
	private final AssetManager assetManager;

	public AnimationResourcesLoader(final AssetManager assetManager)
	{
		super();
		this.assetManager = assetManager;
	}

	public void loadAtlantes()
	{
		assetManager.load("atlantes/reaper" + FileConstants.ATLAS_FILE_ENDING, TextureAtlas.class);
		assetManager.load("atlantes/bald" + FileConstants.ATLAS_FILE_ENDING, TextureAtlas.class);
		assetManager.finishLoading();
	}

	public void loadAnimations()
	{
		final TextureAtlas reaperAtlas = assetManager.get(
				FileConstants.ATLANTES_STANDARD_PATH + "reaper" + FileConstants.ATLAS_FILE_ENDING, TextureAtlas.class);
		final Repository<Animation<AtlasRegion>> reaperRepository = new AnimationRepository("reaper");

		final float frameDuration = 0.275f;
		final Animation<AtlasRegion> reaperUpStand = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_up_stand"));
		reaperRepository.put(AnimationKeys.UP_STAND, reaperUpStand);

		final Animation<AtlasRegion> reaperLeftStand = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_left_stand"));
		reaperRepository.put(AnimationKeys.LEFT_STAND, reaperLeftStand);

		final Animation<AtlasRegion> reaperDownStand = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_down_stand"));
		reaperRepository.put(AnimationKeys.DOWN_STAND, reaperDownStand);

		final Animation<AtlasRegion> reaperRightStand = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_right_stand"));
		reaperRepository.put(AnimationKeys.RIGHT_STAND, reaperRightStand);

		final Animation<AtlasRegion> reaperUpWalk = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_up_walk_1"), reaperAtlas.findRegion("reaper_up_walk_2"));
		reaperRepository.put(AnimationKeys.UP_WALK, reaperUpWalk);

		final Animation<AtlasRegion> reaperLeftWalk = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_left_walk_1"), reaperAtlas.findRegion("reaper_left_walk_2"));
		reaperRepository.put(AnimationKeys.LEFT_WALK, reaperLeftWalk);

		final Animation<AtlasRegion> reaperDownWalk = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_down_walk_1"), reaperAtlas.findRegion("reaper_down_walk_2"));
		reaperRepository.put(AnimationKeys.DOWN_WALK, reaperDownWalk);

		final Animation<AtlasRegion> reaperRightWalk = new Animation<>(frameDuration,
				reaperAtlas.findRegion("reaper_right_walk_1"), reaperAtlas.findRegion("reaper_right_walk_2"));
		reaperRepository.put(AnimationKeys.RIGHT_WALK, reaperRightWalk);

		AnimationRepositories.put("reaper", reaperRepository);

		final TextureAtlas baldAtlas = assetManager.get(
				FileConstants.ATLANTES_STANDARD_PATH + "bald" + FileConstants.ATLAS_FILE_ENDING, TextureAtlas.class);
		final Repository<Animation<AtlasRegion>> baldRepository = new AnimationRepository("bald");

		final Animation<AtlasRegion> baldUpStand = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_up_stand"));
		baldRepository.put(AnimationKeys.UP_STAND, baldUpStand);

		final Animation<AtlasRegion> baldLeftStand = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_left_stand"));
		baldRepository.put(AnimationKeys.LEFT_STAND, baldLeftStand);

		final Animation<AtlasRegion> baldDownStand = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_down_stand"));
		baldRepository.put(AnimationKeys.DOWN_STAND, baldDownStand);

		final Animation<AtlasRegion> baldRightStand = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_right_stand"));
		baldRepository.put(AnimationKeys.RIGHT_STAND, baldRightStand);

		final Animation<AtlasRegion> baldUpWalk = new Animation<>(frameDuration, baldAtlas.findRegion("bald_up_walk_1"),
				baldAtlas.findRegion("bald_up_walk_2"));
		baldRepository.put(AnimationKeys.UP_WALK, baldUpWalk);

		final Animation<AtlasRegion> baldLeftWalk = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_left_walk_1"), baldAtlas.findRegion("bald_left_walk_2"));
		baldRepository.put(AnimationKeys.LEFT_WALK, baldLeftWalk);

		final Animation<AtlasRegion> baldDownWalk = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_down_walk_1"), baldAtlas.findRegion("bald_down_walk_2"));
		baldRepository.put(AnimationKeys.DOWN_WALK, baldDownWalk);

		final Animation<AtlasRegion> baldRightWalk = new Animation<>(frameDuration,
				baldAtlas.findRegion("bald_right_walk_1"), baldAtlas.findRegion("bald_right_walk_2"));
		baldRepository.put(AnimationKeys.RIGHT_WALK, baldRightWalk);

		AnimationRepositories.put("bald", baldRepository);
	}

}
