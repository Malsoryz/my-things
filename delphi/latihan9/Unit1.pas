unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.ComCtrls, Vcl.Buttons;

type
  TFlatihan9 = class(TForm)
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label6: TLabel;
    zodiacdate: TDateTimePicker;
    tzodiac: TEdit;
    thlahir: TEdit;
    SpeedButton1: TSpeedButton;
    ramalanmemo: TMemo;
    procedure zodiacdateChange(Sender: TObject);
    procedure zodiacdateUserInput(Sender: TObject; const UserString: string;
      var DateAndTime: TDateTime; var AllowChange: Boolean);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Flatihan9: TFlatihan9;

implementation

{$R *.dfm}

procedure TFlatihan9.FormCreate(Sender: TObject);
begin
  SpeedButton1.Caption := 'Close';
  SpeedButton1.NumGlyphs := 2;
  SpeedButton1.Flat := True; 
end;

procedure TFlatihan9.zodiacdateChange(Sender: TObject);
Const
  hari:Array[1..7] of String = ('Minggu','Senin','Selasa','Rabu','Kamis','Jum''at','Sabtu');
  zodiac:Array[1..12] of String = ('Aquarius','Pisces','Aries','Taurus','Gemini','Cancer','Leo','Virgo','Libra','Scorpio','Sagitarius','Capricorn');
  ramalan:Array[1..12] of String = (
  'Ide-ide inovatif akan muncul. Jangan ragu untuk berbagi dan mengembangkan konsep baru yang dapat membawa perubahan positif.',
  'Perhatikan kesehatan mental dan emosional Anda. Luangkan waktu untuk meditasi atau aktivitas yang menenangkan.',
  'Hari ini Anda akan merasa penuh energi dan siap menghadapi tantangan. Fokuslah pada tujuan Anda dan hindari konflik yang tidak perlu.',
  'Keuangan Anda mungkin memerlukan perhatian. Pertimbangkan untuk mengatur anggaran dengan lebih baik dan jangan terburu-buru dalam membuat keputusan besar.',
  'Komunikasi dengan orang terdekat sangat penting hari ini. Jadilah pendengar yang baik dan hindari menyebarkan gosip.',
  'Emosi Anda mungkin lebih sensitif dari biasanya. Luangkan waktu untuk diri sendiri dan jangan terlalu keras pada diri sendiri.',
  'Waktu yang baik untuk mengekspresikan diri Anda. Jangan takut untuk menunjukkan kreativitas dan percaya diri Anda dalam segala hal yang Anda lakukan.',
  'Organisasi dan perencanaan adalah kunci sukses Anda hari ini. Fokus pada detail dan selesaikan tugas-tugas yang tertunda.',
  'Hubungan sosial Anda akan berkembang. Manfaatkan peluang untuk memperluas jaringan dan membangun koneksi baru.',
  'Intuisi Anda kuat hari ini. Percayai insting Anda dan ikuti kata hati dalam mengambil keputusan.',
  'Keinginan untuk petualangan dan perubahan akan menguat. Pertimbangkan untuk mencoba hal baru yang dapat memperkaya pengalaman hidup Anda.',
  'Fokus pada karier dan pencapaian tujuan jangka panjang. Kesabaran dan ketekunan akan membawa hasil yang diinginkan.'
  );
var
  yr,mn,dy:Word;
  z:integer;

begin
  DecodeDate(zodiacdate.Date,yr,mn,dy);

  case mn of
  1 :case dy of
    1..20:z:=12;
    21..31:z:=1;
    end;
  2 :case dy of
    1..19:z:=1;
    20..29:z:=2;
    end;
  3 :case dy of
    1..21:z:=2;
    22..31:z:=3;
    end;
  4 :case dy of
    1..20:z:=3;
    21..30:z:=4;
    end;
  5 :case dy of
    1..22:z:=4;
    23..31:z:=5;
    end;
  6 :case dy of
    1..23:z:=5;
    24..30:z:=6;
    end;
  7 :case dy of
    1..23:z:=6;
    24..31:z:=7;
    end;
  8 :case dy of
    1..23:z:=7;
    24..31:z:=8;
    end;
  9 :case dy of
    1..23:z:=8;
    24..30:z:=9;
    end;
  10 :case dy of
    1..23:z:=9;
    24..31:z:=10;
    end;
  11 :case dy of
    1..22:z:=10;
    23..31:z:=11;
    end;
  12 :case dy of
    1..21:z:=11;
    22..31:z:=12;
    end;
    
  end;

  tzodiac.Text := hari[DayofWeek(zodiacdate.Date)];
  thlahir.Text := zodiac[z];
  ramalanmemo.Text := ramalan[z];
end;

procedure TFlatihan9.zodiacdateUserInput(Sender: TObject;
  const UserString: string; var DateAndTime: TDateTime;
  var AllowChange: Boolean);
begin
  AllowChange := True;
end;

end.
