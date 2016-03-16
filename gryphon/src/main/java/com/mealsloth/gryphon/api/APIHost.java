package com.mealsloth.gryphon.api;

/**
 * Created by michael on 3/15/16.
 */
public class APIHost
{
    public enum APIHostEnum
    {
        CHIMERA,
        HYDRA,
    }

    public static String URL(APIHostEnum service)
    {
        switch (service)
        {
            case CHIMERA:
                return "http://api.mealsloth.com/";
            case HYDRA:
                return "http://blob.mealsloth.com/";
        }
        return null;
    }
}
