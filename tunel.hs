module Tunel ( Tunel, newT, listLinksT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun 

listLinksT :: Tunel -> [Link]
listLinksT (Tun link) = listCitiesL link

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = listLinksT `elem` listLinksT tunel

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link tunel = link `elem` listLinksT


--delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
--delayT tunel = listLinksT tunel

