package com.example.wolverine.carcatalogueandroid.async;

import android.os.AsyncTask;

public class AsyncRunnerImpl implements AsyncRunner {
        @Override
        public void runInBackground(Runnable action) {
            runInBackgroundStatic(action);
        }

        private static void runInBackgroundStatic(final Runnable action){
            new AsyncTask<Boolean, Void, Void>(){
                @Override
                protected Void doInBackground(Boolean... booleans) {
                    action.run();
                    return null;
                }
            }.execute(true);
        }
}
