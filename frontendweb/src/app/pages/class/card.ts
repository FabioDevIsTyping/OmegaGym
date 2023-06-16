import { Subscription } from "./subscription"
import { User } from "./user"

export class Card {
    id?:number
    subscription?:Subscription
    startDate?:Date
    endDate?:Date
    user!:User
    
}