clc
clear
tic

srcfolder = 'data';
datalist = dir([srcfolder,'\*.txt']);

file_number= 55;
data = load('data\data56.txt');
data1 = 80 - data(:,1);
data2 = data(:,2:3);
data = [data1,data2];

for i = 1 : file_number
    dataname = datalist(i).name;
    g = load([srcfolder,'\',dataname]);
    data = [data;g];
end

blank = data(:,1);
branch = data(:,2);
cycle = data(:,3);


toc