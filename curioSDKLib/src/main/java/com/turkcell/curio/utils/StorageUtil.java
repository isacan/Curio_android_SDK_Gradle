/*
 * Copyright (C) 2015 Turkcell
 *
 * Created by Can Ciloglu on 29 Haz 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.turkcell.curio.utils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by Can Ciloglu on 29/06/15.
 *
 * @author Can Ciloglu
 */
public class StorageUtil {

    /*
     * Returns available total (internal + external) memory of the device in MB.
     */
    public static long getTotalAvailableMemory(){
        long internalMemory = getAvailableInternalMemory();

        //Due to Android's different perception of "external storage" we simply ignore external storage.
        //long externalMemory = getAvailableExternalMemory();

//        return (internalMemory + externalMemory)/(1024*1024);
        return internalMemory/(1024*1024);
    }

    private static long getAvailableInternalMemory(){
        File path = Environment.getDataDirectory();
        Long usableSpace = path.getUsableSpace();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = 0;
        long availableBlockCount = 0;

        if(Build.VERSION.SDK_INT >= Constants.JELLY_BEAN_MR2_4_3_SDK_INT){
            blockSize = stat.getBlockSizeLong();
            availableBlockCount = stat.getAvailableBlocksLong();
        }else{
            blockSize = stat.getBlockSize();
            availableBlockCount = stat.getAvailableBlocks();
        }
        return availableBlockCount * blockSize;
    }

    private static long getAvailableExternalMemory(){
        File path = Environment.getExternalStorageDirectory();
        Long usableSpace = path.getUsableSpace();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = 0;
        long availableBlockCount = 0;

        if(Build.VERSION.SDK_INT >= Constants.JELLY_BEAN_MR2_4_3_SDK_INT){
            blockSize = stat.getBlockSizeLong();
            availableBlockCount = stat.getAvailableBlocksLong();
        }else{
            blockSize = stat.getBlockSize();
            availableBlockCount = stat.getAvailableBlocks();
        }
        return availableBlockCount * blockSize;
    }
}
