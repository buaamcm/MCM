clc 
clear
load matlab.mat
dis = riverline(:,6);
dis = 2742141 - dis;
dis = dis/1000;
height = riverline(:,4);
plot(dis,height)
axis([0,2743,0,1200]);
set(gca,'XDir','reverse')
xlabel('Distance to Indian Ocean (unit:km)','FontName','Times New Roman','FontWeight','Bold','FontSize',12)
ylabel('Elevation (unit:m)','FontName','Times New Roman','FontWeight','Bold','FontSize',12)
line([1577,1577],[889.9,0],'linestyle',':')
line([1142,1142],[487,0],'linestyle',':')
line([610.1,610.1],[324.1,0],'linestyle',':')
annotation('doublearrow',[0.1315 0.46],[0.14 0.14]);
annotation('doublearrow',[0.46 0.583],[0.14 0.14]);
annotation('doublearrow',[0.583 0.733],[0.14 0.14]);
annotation('doublearrow',[0.73431 0.901],[0.29667 0.29667]);
