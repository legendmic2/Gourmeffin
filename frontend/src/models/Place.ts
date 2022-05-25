import RootStore from "../stores/RootStore";
import {makeAutoObservable} from "mobx";
import IPlace from "../types/Place";

export default class Place implements IPlace {
    id: string;
    name: string;
    address: string;
    placeType: string;
    description: string;
    rating: number;

    constructor(private store: RootStore, place: IPlace) {
        this.id = place.id;
        this.name = place.name;
        this.address = place.address;
        this.placeType = place.placeType;
        this.description = place.description;
        this.rating = place.rating;

        makeAutoObservable(this); // makeObservable으로 했더니 오류 발생
        // mobx no annotations were passed to makeobservable but no decorated members have been found either
    }

    // @computed get user() {
    //     return this.store.user.all.find((it) => it.id === this.vendorId);
    // }
    //
    // @computed get reviews() {
    //     return this.store.review.all.filter((it) => it.productId === this.id)
    // }
}