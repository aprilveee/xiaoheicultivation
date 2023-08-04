package dev.aprilvee.xiaoheic.cultivation;

import dev.aprilvee.xiaoheic.registry.tags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class EnvironmentQi {

    public static float[] getEnviroQi(BlockPos pos, Level level, int diameter){
        float[] qi = new float[6];
        float[] result;
        int radius;
        if(diameter % 2 != 0){
            radius = (diameter - 1)/2;
        }else{radius = diameter/2;}
        BlockPos starterPos = pos.offset(radius,radius,radius);;
        BlockPos currentPos = starterPos;

        //level.getBlockState(currentPos).getBlock();

        for(int x = 0; x < diameter; x++){ //forward to back
            for(int y = 0; y < diameter; y++){ //top to bottom
                for(int z = 0; z < diameter; z++) { //left to right
                    result = getSpirit(level.getBlockState(currentPos).getBlock());
                    qi[0] += result[0];
                    qi[1] += result[1];
                    qi[2] += result[2];
                    qi[3] += result[3];
                    qi[4] += result[4];
                    qi[5] += result[5];

                    currentPos.offset(0, 0 , -1);
                    //shift block z
                }
                currentPos.offset(0, -1, 0);
                //shift block y
            }
            currentPos.offset(-1, 0, 0);
            //shift block x
        }
        return qi;
    }

    public static float[] getSpirit(Block block){
        //i think that i am commiting crimes
        float[] qi = new float[6];
        if(ForgeRegistries.BLOCKS.tags().getTag(tags.spiritstrong).contains(block)){ qi[0] = 5;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.spiritweak).contains(block)){ qi[0] = 1;
        }

        if(ForgeRegistries.BLOCKS.tags().getTag(tags.metalstrong).contains(block)){ qi[1] = 5;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.metal).contains(block)){ qi[1] = 2.5f;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.metalweak).contains(block)){ qi[1] = 1;
        }

        if(ForgeRegistries.BLOCKS.tags().getTag(tags.waterstrong).contains(block)){qi[2] = 5;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.water).contains(block)){qi[2] = 2.5f;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.waterweak).contains(block)){qi[2] = 1;
        }

        if(ForgeRegistries.BLOCKS.tags().getTag(tags.woodstrong).contains(block)){qi[3] = 5;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.wood).contains(block)){qi[3] = 2.5f;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.woodweak).contains(block)){qi[3] = 1;
        }

        if(ForgeRegistries.BLOCKS.tags().getTag(tags.firestrong).contains(block)){qi[4] = 5;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.fire).contains(block)){qi[4] = 2.5f;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.fireweak).contains(block)){qi[4] = 1;
        }

        if(ForgeRegistries.BLOCKS.tags().getTag(tags.earthstrong).contains(block)){qi[5] = 5;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.earth).contains(block)){qi[5] = 2.5f;
        } else if(ForgeRegistries.BLOCKS.tags().getTag(tags.earthweak).contains(block)){qi[5] = 1;
        }

        return qi;
    }
}
