package extech.tileentity.misc;

import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import net.minecraft.block.Block;

import static gregapi.data.CS.FACES_TBS;
import static gregapi.data.CS.L6_IICONCONTAINER;

public class MultiTileEntityMiner extends TileEntityBase09FacingSingle {
    public IIconContainer[] mTextures = L6_IICONCONTAINER, mTexturesFront = L6_IICONCONTAINER;

    @Override
    public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
        return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get((aSide==mFacing?mTexturesFront:mTextures)[FACES_TBS[aSide]+3])) : null;
    }

    @Override
    public boolean canDrop(int aSlot) {
        return true;
    }

    @Override
    public String getTileEntityName() {
        return "";
    }
}
