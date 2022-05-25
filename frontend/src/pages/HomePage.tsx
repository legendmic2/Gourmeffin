import React, {useEffect, useState} from 'react';
import {observer} from "mobx-react-lite";
import {useAppContext} from "../app-context";
import Place from "../components/Place";

const HomePage = observer(() => {
    const {api, store} = useAppContext();
    const [loading, setLoading] = useState(false);

    const load = async () => {
        try {
            setLoading(true);
            await api.place.getAll();
            // User, Review
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        load();
    }, []);

    if (loading) {
        return <div>loading...</div>;
    }

    return (
        <div>
            <h1>Places</h1>
            {store.place.all.map((place) => (
                <Place place={place} key={place.id}/>
            ))}
        </div>
    );
});

export default HomePage;
