package com.turkcell.curio;

/**
 * Created by Can Ciloglu on 08/07/15.
 *
 * @author Can Ciloglu
 */
public interface IUnregisterListener {
    public void onUnregisterResponse(boolean isSuccessful, int statusCode);
}
