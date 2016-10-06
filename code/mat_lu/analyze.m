clc
clear
tic

srcfolder = 'data';
datalist = dir([srcfolder,'\*.txt']);

file_number= 55;
data = load('data56.txt');
data1 = 80 - data(:,1);
data2 = data(:,2:3);
data = [data1,data2];

for i = 1 : file_number
    dataname = datalist(i).name;
    g = load([srcfolder,'\',dataname]);
    data = [data;g];
end
datar = [];
datas = sortrows(data);
[r,c] = size(datas);
for i = 1 : 60
    temp = datas(datas(:,1) == i,:);
    avg = mean(temp);
    datar = [datar;avg];
end
blank = datar(:,1);
branch = datar(:,2);
cycle = datar(:,3);


toc