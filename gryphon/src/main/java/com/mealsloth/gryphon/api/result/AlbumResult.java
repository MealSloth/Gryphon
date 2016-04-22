package com.mealsloth.gryphon.api.result;

import com.mealsloth.gryphon.models.AlbumModel;

import java.util.ArrayList;

/**
 * Created by michael on 4/20/16.
 */
public class AlbumResult
{
    public AlbumModel album;

    public AlbumResult(ArrayList results)
    {
        if (results.get(0) instanceof AlbumModel)
            this.album = (AlbumModel)results.get(0);
        else
            System.out.println("Did not receive album object");
    }
}
