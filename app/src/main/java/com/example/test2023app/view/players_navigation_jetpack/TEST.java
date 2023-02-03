package com.example.test2023app.view.players_navigation_jetpack;

import android.util.Log;

public class TEST {

    private int mul(int n, int index) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = mul(n, i);
            Log.d("MUL", result + "");
        }
        return result;
    }

    public static void main(String[] args) {
        new TEST().mul(10,10);
    }
}
