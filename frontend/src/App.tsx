import React from 'react';
import './App.css';
import RootStore from "./stores/RootStore";
import RootApi from "./apis/RootApi";
import AppContext from "./app-context";
import {Route, Routes} from "react-router-dom";
import HomePage from "./pages/HomePage";

const store = new RootStore();
const api = new RootApi(store);

function App() {
    return (
        <AppContext.Provider value={{store, api}}>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
            </Routes>
        </AppContext.Provider>
    );
}

export default App;
