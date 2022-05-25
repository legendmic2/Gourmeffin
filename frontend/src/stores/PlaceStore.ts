import {action, computed, makeAutoObservable, observable} from "mobx";
import RootStore from "./RootStore";
import Place from "../models/Place";
import IPlace from "../types/Place";


export default class PlaceStore {

    byId = observable.map<string, Place>();

    constructor(private store: RootStore) {
        makeAutoObservable(this);
    }

    @action load(places: IPlace[]) {
        places.forEach((it) => this.byId.set(it.id, new Place(this.store, it)));
    }

    @computed get all() {
        return Array.from(this.byId.values());
    }

    @action add(place: IPlace) {

    }

}