package model;

import java.util.List;

/**
 * @author Emily Wang
 * Firebase Callback Interface used for loading asynchronous data
 */

public interface FireBaseCallBack {
    /**
     * CallBack Function
     * @param list to be utilized in classes that implement this interface
     */
    void onCallBack(List<String> list);
}
