import {clsx} from "clsx"
import {twMerge} from "tailwind-merge"

export function cn(...inputs) {
    //kjhkjh
    return twMerge(clsx(inputs))
}
