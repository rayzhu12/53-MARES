package model;

import java.util.List;

/**
 * @author Emily Wang
 * Firebase Callback Interface used for loading asynchronous data
 */

public interface FireBaseCallBack {
    void onCallBack(List<String> list);
}
