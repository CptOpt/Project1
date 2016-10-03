package cogentdatasolutions.project1.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cogentdatasolutions.project1.R;


/**
 * Created by Divya on 6/13/2016.
 */
//
public class Company extends Fragment{

    Toolbar toolbar;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;




//    public  class ToolbarDisp extends AppCompatActivity implements SearchView.OnQueryTextListener{
//
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setHasOptionsMenu(true);
//            //View view = getLayoutInflater().inflate(R.layout.toolbar,null);
//            toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
//            //toolbar.setTitle("hello ");
//        }
//
//        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.searchmenu, menu);
//
//            MenuItem searchItem = menu.findItem(R.id.search);
//            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//            searchView.setOnQueryTextListener(this);
//
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            return false;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            return false;
//        }
//    }

  // @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.company,container,false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("search companies");

        toolbar.inflateMenu(R.menu.searchmenu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onOptionsItemSelected(item);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.searchmenu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setQueryHint("Search");
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(queryTextListener);
    }

   // @Override
   // public void onCreate(@Nullable Bundle savedInstanceState) {
     //   super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);
    }
//
  //  @Override
   // public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
     //   super.onCreateOptionsMenu(menu, inflater);

       // inflater.inflate(R.menu.searchmenu,menu);
        //MenuItem searchItem = menu.findItem(R.id.search);
      //  SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

       // if(searchItem != null){
          //  searchView = (SearchView) searchItem.getActionView();
  //      }
//
       // if(searchView != null){
     //       searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
          //  queryTextListener = new SearchView.OnQueryTextListener(){

              //  @Override
                //public boolean onQueryTextSubmit(String query) {
                  //  return true;
              //  }

             //   @Override
              //  public boolean onQueryTextChange(String newText) {
             //       return true;
               // }
           // };
       // }



       // searchView.setOnQueryTextListener(queryTextListener);

   // }

   // @Override
    //public boolean onOptionsItemSelected(MenuItem item) {

        //switch (item.getItemId()){
      //      case R.id.search:
        //        return false;
          //  default:
            //    break;
        //}
        //searchView.setOnQueryTextListener(queryTextListener);
        //return super.onOptionsItemSelected(item);
   // }
//}