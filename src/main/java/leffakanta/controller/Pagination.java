package leffakanta.controller;

import javax.servlet.http.HttpSession;

public class Pagination {
    
    // get page size for user's device
    public static int getPageSize(HttpSession session){
        // only 10 items on desktop and 5 items on mobile shows for easier demonstration purposes
        // better values would be 50 items for desktop and 20 items for mobile
        int size = 5;
        if ((Boolean)session.getAttribute("desktop")==true){
            size = 10;
        }
        return size;
    }
    
    // get page count
    public static int getPageCount(int pageSize, int itemCount){
        int count = (int)Math.ceil((double)itemCount/pageSize);
        return count;
    }
    

}
