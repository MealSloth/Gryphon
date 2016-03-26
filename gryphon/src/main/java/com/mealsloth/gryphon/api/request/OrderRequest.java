package com.mealsloth.gryphon.api.request;

import com.mealsloth.gryphon.activities.AbstractBaseActivity;
import com.mealsloth.gryphon.api.APIHost.APIHostEnum;
import com.mealsloth.gryphon.api.APIModelProcessor;
import com.mealsloth.gryphon.api.APIParameter;
import com.mealsloth.gryphon.api.JsonPost;
import com.mealsloth.gryphon.models.OrderModel;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by michael on 3/15/16.
 */
public class OrderRequest extends AbstractAPIRequest
{
    public final static String METHOD_ORDER = "Order";

    public OrderRequest activity(AbstractBaseActivity activity)
    {
        this.activity = activity;
        return this;
    }

    public String getCurrentClassName()
    {
        return OrderRequest.class.getCanonicalName();
    }

    public OrderRequest methodOrder(String orderID)
    {
        this.methodName = METHOD_ORDER;
        this.parameters.put(APIParameter.PARAM_CLIENT_ORDER_ID, orderID);
        return this;
    }

    public OrderModel Order(HashMap request)
    {
        HashMap<String, String> data = new HashMap<>();
        String orderID = (String)request.get(APIParameter.PARAM_CLIENT_ORDER_ID);
        data.put(APIParameter.PARAM_SERVER_ORDER_ID, orderID);
        try
        {
            String response = new JsonPost(APIHostEnum.CHIMERA, "order/", data).post();
            HashMap result = new ObjectMapper().readValue(response, HashMap.class);
            result = (HashMap)result.get(APIParameter.PARAM_ORDER);
            return APIModelProcessor.ProcessOrderModel(result);
        }
        catch (IOException error)
        {
            return null;
        }
    }
}
