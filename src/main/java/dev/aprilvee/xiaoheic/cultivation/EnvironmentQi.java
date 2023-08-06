package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.registry.tags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public class EnvironmentQi {
    static float veryhigh = 18;
    static float high = 6;
    static float moderate = 2;
    static float low = 0.25f;
    static float verylow = 0.04f;

    public static float[] getEnviroQi(BlockPos pos, Level level, int diameter){
        float[] qi = new float[7];
        float[] result;
        int radius;
        if(diameter % 2 != 0){
            radius = (diameter - 1)/2;
        }else{radius = diameter/2;}
        BlockPos starterPos = pos.offset(radius,radius,radius);
        BlockPos currentPos = starterPos;

        //level.getBlockState(currentPos).getBlock();

        for(int x = 0; x < diameter; x++){ //forward to back
            for(int y = 0; y < diameter; y++){ //top to bottom
                for(int z = 0; z < diameter; z++) { //left to right
                    result = getSpirit(currentPos, level);
                    qi[0] += result[0];
                    qi[1] += result[1];
                    qi[2] += result[2];
                    qi[3] += result[3];
                    qi[4] += result[4];
                    qi[5] += result[5];
                    qi[6] += result[6];
                    currentPos = currentPos.offset(0, 0 , -1);
                    //shift block z
                }
                currentPos = currentPos.offset(0, -1, diameter);
                //shift block y
            }
            currentPos = currentPos.offset(-1, diameter, 0);
            //shift block x
        }
        return qi;
    }

    public static float[] getSpirit(BlockPos pos, Level level){
        //i think that i am commiting crimes
        Block block = level.getBlockState(pos).getBlock();
        Fluid fluid = level.getFluidState(pos).getType();
        float[] qi = {0, 0, 0, 0, 0, 0, 0};
        ITagManager<Block> tag = ForgeRegistries.BLOCKS.tags();
        ITagManager<Fluid> fluidtag = ForgeRegistries.FLUIDS.tags();

            if(tag.getTag(tags.spiritvstrong).contains(block)){ qi[0] = veryhigh;
            } else if(tag.getTag(tags.spiritstrong).contains(block)){ qi[0] = high;
            } else if(tag.getTag(tags.spirit).contains(block)){ qi[0] = moderate;
            } else if(tag.getTag(tags.spiritweak).contains(block)){ qi[0] = low;
            }

            if(tag.getTag(tags.metalstrong).contains(block)){ qi[1] = high;
            } else if(tag.getTag(tags.metal).contains(block)){ qi[1] = moderate;
            } else if(tag.getTag(tags.metalweak).contains(block)){ qi[1] = low;
            }

            if(tag.getTag(tags.waterstrong).contains(block)){qi[2] = high;
            } else if(tag.getTag(tags.water).contains(block)){qi[2] = moderate;
            } else if(tag.getTag(tags.waterweak).contains(block)){qi[2] = low;
            }

            if(tag.getTag(tags.woodstrong).contains(block)){qi[3] = high;
            } else if(tag.getTag(tags.wood).contains(block)){qi[3] = moderate;
            } else if(tag.getTag(tags.woodweak).contains(block)){qi[3] = low;
            }

            if(tag.getTag(tags.firestrong).contains(block)){qi[4] = high;
            } else if(tag.getTag(tags.fire).contains(block)){qi[4] = moderate;
            } else if(tag.getTag(tags.fireweak).contains(block)){qi[4] = low;
            }

            if(tag.getTag(tags.earthstrong).contains(block)){qi[5] = high;
            } else if(tag.getTag(tags.earth).contains(block)){qi[5] = moderate;
            } else if(tag.getTag(tags.earthweak).contains(block)){qi[5] = low;
            } else if(tag.getTag(tags.earthveryweak).contains(block)){qi[5] = verylow;
            }

            if(fluidtag.getTag(tags.waterfluid).contains(fluid)){qi[2] += low;
            }else if(fluidtag.getTag(tags.lava).contains(fluid)){qi[4] += moderate;
            }

        if(tag.getTag(tags.solid).contains(block)){qi[6] = 1;} //crampedness
        return qi;
    }
}
