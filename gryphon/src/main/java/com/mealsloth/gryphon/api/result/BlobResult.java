package com.mealsloth.gryphon.api.result;

import com.mealsloth.gryphon.models.BlobModel;

import java.util.ArrayList;

/**
 * Created by michael on 4/20/16.
 */
public class BlobResult
{
    public BlobModel blob;
    public ArrayList<BlobModel> blobs;

    public BlobResult(ArrayList results)
    {
        if (results.get(0) instanceof BlobModel)
            this.blob = (BlobModel)results.get(0);
        else
            this.blobs = (ArrayList<BlobModel>)results.get(0);
    }
}
