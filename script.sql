SELECT * from vol  as v left join paramvol as p on v.idvol=p.idvol;


drop view v_statistique;
create or  replace view v_statistique as SELECT res.id_utilisateur,v.*,p.date_butoire_paiement,res.classe,p.classe as paramClass,res.dt_reservation,p.nbr_billet,p.promotion,paiement 
from vol  as v 
left join 
paramvol as p on v.id_vol=p.idvol
left join
reservation as res
on v.id_vol=res.id_vol and res.dt_reservation <=date_butoire_paiement


select COUNT(*) * promotion,date_butoire_paiement,id_vol,paiement  from v_statistique where paiement=true group by date_butoire_paiement,id_vol,paiement;

select COUNT(*) * promotion,date_butoire_paiement from v_statistique where date_butoire_paiement>='2025-08-17';