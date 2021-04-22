    package com.example.andr3lesson2.ui;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.fragment.app.Fragment;
    import androidx.navigation.NavController;
    import androidx.navigation.Navigation;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.andr3lesson2.R;
    import com.example.andr3lesson2.data.model.Film;
    import com.example.andr3lesson2.data.remote.KeepFilms;

    import java.util.ArrayList;
    import java.util.List;

    public class StartFragment extends Fragment {

        private Adapter adapter;
        private RecyclerView recyclerView;
        private List<Film> listFilm = new ArrayList<>();
        private NavController navController;

        public StartFragment() {

        }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        navController = Navigation.findNavController(getActivity(),
                R.id.navHostFragment);

        loadingFilm(view);
        return view;
    }

        private void loadingFilm(View view) {
            KeepFilms.getListFilmId(new KeepFilms.Result() {
                @Override
                public void onSuccess(List<Film> film) {
                    listFilm.clear();
                    listFilm.addAll(film);
                    init(view);
                }

                @Override
                public void onFailure(String error) {

                }
            });
        }

        private void init(View view) {
            recyclerView = view.findViewById(R.id.rvBasicFragment);
            adapter = new Adapter();

            adapter.setListener(film -> {
            String filmId = film.getId();

            Bundle bundle = new Bundle();
            bundle.putString("film", filmId);

            NavController navController = Navigation.findNavController(getActivity(),
                    R.id.navHostFragment);
            navController.navigate(R.id.secondFragment, bundle);

            });
            adapter.addList(listFilm);
            recyclerView.setAdapter(adapter);

        }
    }