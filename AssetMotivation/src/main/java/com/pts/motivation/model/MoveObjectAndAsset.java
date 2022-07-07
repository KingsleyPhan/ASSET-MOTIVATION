package com.pts.motivation.model;

public class MoveObjectAndAsset {
	private MoveObject moveObject;
	
	private AssetMoveObjectDetail asset;
	
	public MoveObjectAndAsset() {
		this.asset = new AssetMoveObjectDetail();
		this.moveObject = new MoveObject();
	}

	public MoveObject getMoveObject() {
		return moveObject;
	}

	public void setMoveObject(MoveObject moveObject) {
		this.moveObject = moveObject;
	}

	public AssetMoveObjectDetail getAsset() {
		return asset;
	}

	public void setAsset(AssetMoveObjectDetail asset) {
		this.asset = asset;
	}
	
	
}
